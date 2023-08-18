import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static LinkedList<LinkedList<Integer>> adjList;
	static boolean[] visited;
	
	static StringBuilder answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		adjList = new LinkedList<>();
		visited = new boolean[n + 1];
		
		for (int i = 0; i <= n; i++) {
			adjList.add(new LinkedList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(to);
			adjList.get(to).add(from);
			
		}
		
		for (int i = 1; i <= n; i++) {
			Collections.sort(adjList.get(i));
		}
		
		visited[start] = true;
		dfs(start);
		answer.append("\n");
		Arrays.fill(visited, false);
		bfs(start);
		System.out.println(answer.toString());
		
	}
	
	
	private static void bfs(int vertex) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(vertex);
		visited[vertex] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			answer.append(String.format("%d ", current));
			for (int to : adjList.get(current)) {
				if (!visited[to]) {
					visited[to] = true;
					queue.offer(to);
				}
			}
			
		}
		
		answer.append("\n");
		
	}
	
	private static void dfs(int vertex) {
		
		answer.append(String.format("%d ", vertex));
		
		for (int to : adjList.get(vertex)) {
			if (!visited[to]) {
				visited[to] = true;
				dfs(to);
//				visited[temp.to] = false;
			}
		}
		
	}

}