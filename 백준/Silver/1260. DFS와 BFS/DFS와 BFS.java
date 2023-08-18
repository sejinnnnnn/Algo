import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1260. DFS와 BFS
 * 기본적인 DFS와 BFS를 구현하는 문제임
 * 2달전에 파이썬으로 풀었음에도 불구하고 자바로 푸는 데 조금 틀렸다
 * 일단 정점들을 sort했어야 했기 때문에 이중 LinkedList로 인접리스트를 구현하였다.
 * 메서드에서 정점 하나 방문할 때 마다 정답 문자열에 하나씩 추가해주었다 !
 * 메모리 : 21080KB, 시간 : 416ms
 * @author SSAFY
 *
 */
public class Main {
	
	static int n;
	static int m;
	static ArrayList<ArrayList<Integer>> adjList;
	static boolean[] visited;
	
	static StringBuilder answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		visited = new boolean[n + 1];
		
		for (int i = 0; i <= n; i++) {
			adjList.add(new ArrayList<>());
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