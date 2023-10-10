import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<List<Integer>> adjustList = new ArrayList<>();
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			adjustList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			adjustList.get(from).add(to);
			adjustList.get(to).add(from);
		}
		
		int groupCount = 0;
		
		while (true) {
			
			// 아직 방문하지 않은 정점을 시작으로 탐색하기 위해, 시작 정점 찾기
			int start = -1;
			
			// n번 반복하며 방문하지 않은 i 값을 start에 대입
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					start = i;
					break;
				}
			}
			
			// 만약 start가 초기값인 -1이라면 모든 정점을 방문한 것이므로 반복문 탈출
			if (start == -1) break;
			
			// 찾은 미방문 정점을 시작으로 탐색 시작
			
			// BFS 시 사용할 queue 생성 후 시작 정점 추가, 방문 처리
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			queue.add(start);
			visited[start] = true;
			
			while (!queue.isEmpty()) {
				int current = queue.poll();
				
				for (int next : adjustList.get(current)) {
					if (!visited[next]) {
						visited[next] = true;
						queue.add(next);
					}
				}
				
			}
			
			
			groupCount++;
		}
		
		System.out.println(groupCount);
		
	}

}