import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * D4 7465. 창용 마을 무리의 개수
 * 사람을 정점으로 보고, 친구관계를 간선으로 보기
 * 서로 친구이기 때문에 a->b, b->a를 둘 다 인접 리스트에 추가해 줘야 함
 * 리스트에 추가한 후, 모든 정점을 방문할 때 까지 임의의 미방문 점을 찍고 BFS로 탐색하였음
 * 
 * 메모리 : 27,408 KB, 시간 : 139 ms
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
		
		for (int t = 1; t <= testCase; t++) {
			
			// 공백으로 문자열 구분해서 n, m에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			// 리스트 안에 리스트가 있는 구조인 인접리스트 생성
			List<List<Integer>> adjustList = new ArrayList<>();
			
			// 정점 수 만큼 리스트 안에 리스트를 하나씩 생성해서 넣어주기
			for (int i = 0; i < n; i++) {
				adjustList.add(new ArrayList<>());
			}
			
			// 방문배열, 방문한 정점 개수 저장할 변수
			boolean[] visited = new boolean[n];
			int visitCount = 0;
			
			// 간선 수 만큼 공백으로 문자열 구분해서 서로의 인접 리스트에 넣기
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				adjustList.get(from).add(to);
				adjustList.get(to).add(from);
			}
			
			// 그룹의 개수
			int groupCount = 0;
			
			// 방문한 정점의 개수가 n보다 작다면 계속 반복
			while (visitCount < n) {
				
				// 방문하지 않은 임의의 정점
				int start = -1;
				
				// 방문하지 않았다면 start에 i를 넣기
				for (int i = 0; i < n; i++) {
					if (!visited[i]) {
						start = i;
						break;
					}
				}
				
				// queue에 시작점 넣고 방문처리, 방문 카운트 + 1
				ArrayDeque<Integer> queue = new ArrayDeque<>();
				queue.add(start);
				visited[start] = true;
				visitCount++;
				
				// BFS로 탐색 실시
				while (!queue.isEmpty()) {
					int current = queue.poll();
					
					// 큐에서 꺼낸 값의 인접리스트를 보며 방문하지 않았다면 큐에 추가, 방문처리, 방문 횟수 + 1
					for (int next : adjustList.get(current)) {
						if (!visited[next]) {
							visited[next] = true;
							visitCount++;
							queue.add(next);
						}
					}
					
				}
				
				// 해당 정점에서의 탐색이 종료되었다면 그룹 숫자 + 1
				groupCount++;
			}
			
			// 반복문 종료 후 그룹의 숫자를 정답 문자열에 추가
			answer.append(String.format("#%d %d\n", t, groupCount));
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}

}