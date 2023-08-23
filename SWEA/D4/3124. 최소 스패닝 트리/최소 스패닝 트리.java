import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static int v;
	static int e;
	
	static int[][] edges;
	
	static ArrayList<ArrayList<int[]>> vEdges;
	
	static int total;
	
	static int[] parents;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			total = 0;
			
			edges = new int[e][3];
			vEdges = new ArrayList<>();
			
			for (int i = 0; i <= v; i++) {
				vEdges.add(new ArrayList<>());
			}
			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				edges[i][0] = Integer.parseInt(st.nextToken());
				edges[i][1] = Integer.parseInt(st.nextToken());
				edges[i][2] = Integer.parseInt(st.nextToken());
				
				vEdges.get(edges[i][0]).add(edges[i]);
			}
			
			answer.append(String.format("#%d %d\n", t, kruskal()));
//			answer.append(String.format("#%d %d\n", t, prim()));
			
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	
	private static int prim() {
		
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		boolean[] visited = new boolean[v + 1];
		
		int result = 0;
		
		int visitCount = 1;
		visited[0] = true;
		
		for (int[] edge : vEdges.get(0)) {
			pQueue.add(edge);
		}
		 
		while (!pQueue.isEmpty()) {
			int[] currentEdge = pQueue.poll();
			
			if (!visited[currentEdge[1]]) {
				visited[currentEdge[1]] = true;
				result += currentEdge[2];
				
				for (int[] edge : vEdges.get(currentEdge[1])) {
					pQueue.add(edge);
				}
				visitCount++;
			}
			
//			System.out.println(Arrays.toString(currentEdge) + " " + result);
//			System.out.println(Arrays.toString(visited));
			
			if (visitCount == v) return result;
			
		}
		
		return -1;
	}
	
	private static long kruskal() {
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
			
		});
		
		parents = new int[v + 1];
		
		for (int i = 1; i <= v; i++) {
			parents[i] = i;
		}
		
		long result = 0;
		int select = 0;
		
		for (int[] current : edges) {
			
			if (union(current[0], current[1])) {
				select++;
				result += current[2];
			}
			
			if (select == v - 1) return result;
			
		}
		
		return -1;
	}
	
	private static int find(int a) {
		if (a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		return true;
		
	}
	
	
}