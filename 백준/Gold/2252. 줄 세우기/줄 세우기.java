import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder answer = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> adjustList = new ArrayList<>();
		int[] indegrees = new int[n + 1];
		
		boolean[] visited = new boolean[n + 1];
		
		for (int i = 0; i <= n; i++) {
			adjustList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjustList.get(from).add(to);
			indegrees[to]++;
			
		}
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegrees[i] == 0) {
				visited[i] = true;
				queue.add(i);
				answer.append(String.format("%d ", i));
			}
		}
		
		while (!queue.isEmpty()) {
			
			int current = queue.poll();
			
			for (int i = 0; i < adjustList.get(current).size(); i++) {
				int to = adjustList.get(current).get(i);
				indegrees[to]--;
				if (indegrees[to] == 0) {
					visited[to] = true;
					queue.add(to);
					answer.append(String.format("%d ", to));
				}
			}
			
		}
		
		System.out.println(answer.toString().trim());
		
	}

}