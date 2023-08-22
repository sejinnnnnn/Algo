import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4 3289. 서로소 집합
 * 오늘 배운 union-find 알고리즘을 사용하여 문제를 해결하였음
 * Path compression을 적용하여 find 메서드 구현 + 같은 부모를 가지고 있다면 같은 집합인 것으로 판단
 * n이 1,000,000 이고 m이 100,000 이기 때문에 범위가 크다 !
 * 메모리 : 106804KB, 시간 : 447ms
 * @author SSAFY
 *
 */
public class Solution {
	
	// 집합 개수 n 저장할 변수
	static int n;
	
	// 각 요소의 대표 인덱스를 저장할 배열
	static int[] parents;
	

	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 출력 객체 생성
		StringBuilder answer = new StringBuilder();
		
		// 테케 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// 정답 문자열에 테케 번호 추가하기
			answer.append(String.format("#%d ", t));
			
			// 입력받아 공백으로 구분
			st = new StringTokenizer(br.readLine());
			
			// 각각 n과 m에 int로 파싱하여 대입
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			// 인덱스 0 ~ n까지의 범위를 가지는 parents 배열 생성
			parents = new int[n + 1];
			
			// 1부터 n까지 반복하며 parents 배열의 i번째 인덱스에 자기 자신의 요소 값 i를 넣어줌 (초기에 서로소이기 때문에)
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			// 명령어 수 m개만큼 반복
			for (int i = 0; i < m; i++) {
				
				// 공백으로 문자열 구분
				st = new StringTokenizer(br.readLine());
				
				// 만약 0으로 시작하면 (Union 연산)
				if (st.nextToken().equals("0")) {
					// 다음토큰 , 다다음토큰 int로 파싱하여 union 메서드에 넣어주기
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else { // 1로 시작하면 (Find연산)
					
					// 비교할 요소 a, b를 int로 파싱하여 저장
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					
					// 정답 문자열에 find(a)와 find(b)의 return값을 비교하여 같으면 1, 다르면 0 추가
					answer.append(find(a) == find(b) ? 1 : 0);
					
				}
				
			}
			
			// 개행문자 추가
			answer.append("\n");
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
		
	}
	
	/**
	 * 요소를 입력받아, 그 요소가 속해있는 집합의 대표 요소를 반환
	 * 
	 * @param a 찾을 요소 값
	 * @return 해당 집합의 대표 요소 반환
	 */
	private static int find(int a) {
		
		if (a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
		
	}
	
	/**
	 * 요소 두 개를 입력받아, 각각의 두 개의 다른 집합을 하나로 합치는 Union 메서드
	 * @param a 요소 1
	 * @param b 요소 2
	 * @return 정상적으로 합쳐졌으면 true, 아니면 (두개가 이미 같은 집합이면) false
	 */
	private static boolean union(int a, int b) {
		
		// a의 대표, b의 대표 요소를 find로 찾아옴
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 만약 두 값이 같다면 (같은 집합이라면) false를 반환
		if (aRoot == bRoot) return false;
		
		// 다르다면 b요소의 parent 값을 a요소의 parent 값으로 갱신
		parents[bRoot] = aRoot;
		
		// true 반환
		return true;
	}

}