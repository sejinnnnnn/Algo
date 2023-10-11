import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4 8382. 방향 전환
 * 처음에 BFS 탐색을 생각했으나, 범위가 200 * 200이어서 시간 초과가 날 것 같았다
 * 그래서 수식으로 방향을 틀어서 생각해 보았다 !
 * y = x 그래프 위의 한 점에서 해당 점까지의 가장 가까운 거리는 수직으로 만나는 접선이고, 따라서 기울기의 곱 = -1을 생각해서 좌표를 구했다 !!
 * 
 * 메모리 : 18,868 KB, 시간 : 109 ms
 * @author 세진
 *
 */
public class Solution {
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 테케 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// 공백으로 문자열 구분해서 시작 좌표 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 시작점을 (0, 0), 도착점을 1사분면 양수로 만들기 위해 두 값을 빼준 뒤 절댓값을 씌워 저장하기
			int x = Math.abs(x2 - x1);
			int y = Math.abs(y2 - y1);
			
			// y = x 그래프 위에서 가장 가까운 점 좌표 찾기
			int point = (x + y) / 2;
			
			// (x, y) 부터 (point, point) 까지의 거리 구하기
			int additional = Math.abs(x - point) + Math.abs(y - point);
			
			// 정답 추가
			answer.append(String.format("#%d %d\n", t, 2 * point + additional));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}

}