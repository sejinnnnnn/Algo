import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 2252. 줄 세우기
 * 위상정렬 개념을 이용하여 해결
 * 인접리스트를 만들고, 입력받을 때 indegree 값을 계산해주고
 * queue에 넣을 때 indegree 값이 0인 것들을 넣어주고, 빼면서 이어진 indegree값을 갱신해주는 식으로 해결하였음
 * 메모리 : 81268KB, 시간 : 780ms
 * 
 * @author 세진
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 공백으로 문자열 구분
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n, m 저장
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 2차원 List로 인접리스트 구현
		ArrayList<ArrayList<Integer>> adjustList = new ArrayList<>();
		
		// 진입차수 계산해서 저장할 indegrees 배열
		int[] indegrees = new int[n + 1];
		
		// 방문배열 (이 문제에선 필요없지만 그래프 탐색이니까 ㅠ)
		boolean[] visited = new boolean[n + 1];
		
		// n + 1번 돌면서 list에 새 list를 추가
		for (int i = 0; i <= n; i++) {
			adjustList.add(new ArrayList<>());
		}
		
		// m번 반복
		for (int i = 0; i < m; i++) {
			// 공백으로 문자열 구분
			st = new StringTokenizer(br.readLine());
			
			// 어디에서 어디로 가는지 from to에 저장
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 인접리스트 from번째 리스트에 to를 추가
			adjustList.get(from).add(to);
			
			// to의 차수 1 증가
			indegrees[to]++;
			
		}
		
		// queue 생성
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// n개를 보면서 차수가 0인 번호를 다 queue에 추가 + 방문배열 true로 갱신 + 정답 문자열에 추가
		for (int i = 1; i <= n; i++) {
			if (indegrees[i] == 0) {
				visited[i] = true;
				queue.add(i);
				answer.append(String.format("%d ", i));
			}
		}
		
		// queue가 빌 때 까지 반복
		while (!queue.isEmpty()) {
			
			// queue에서 첫 번째 값 빼기
			int current = queue.poll();
			
			// 뺀 값의 인접리스트의 크기만큼 반복
			for (int i = 0; i < adjustList.get(current).size(); i++) {
				// i번째 인덱스 가져옴
				int to = adjustList.get(current).get(i);
				// 가져온 값의 차수 -1
				indegrees[to]--;
				// 만약 차수가 0이면 방문배열 갱신 + 큐에 추가 + 정답문자열에 추가
				if (indegrees[to] == 0) {
					visited[to] = true;
					queue.add(to);
					answer.append(String.format("%d ", to));
				}
			}
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}

}