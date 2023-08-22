import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	
	static ArrayList<ArrayList<Integer>> adjustList;
	static boolean[] visited;
	static int visitedCount;
	
	static int depthCount;
	static int maxDepthCount;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		adjustList = new ArrayList<>();
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			adjustList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjustList.get(from).add(to);
			adjustList.get(to).add(from);
			
		}
		
		int vertex = 0;
		visitedCount = 0;
		depthCount = 0;
		
		for (int i = 0; i < n; i++) {
			dfs(i);
			Arrays.fill(visited, false);
		}
		
		System.out.println(0);
		
	}
	
	private static void dfs(int vertex) {
		
		visited[vertex] = true;
		visitedCount++;
		
		if (depthCount == 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		for (int i = 0; i < adjustList.get(vertex).size(); i++) {
			int next = adjustList.get(vertex).get(i);
			if (!visited[next]) {
				depthCount++;
				dfs(next);
				depthCount--;
				visited[next] = false;
			}
		}
		
		
	}
	
}