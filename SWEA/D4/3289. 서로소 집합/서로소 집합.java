import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int[] parents;
	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			answer.append(String.format("#%d ", t));
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				if (st.nextToken().equals("0")) {
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else {
					
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					
					answer.append(find(a) == find(b) ? 1 : 0);
					
				}
				
			}
			
			answer.append("\n");
			
		}
		
		System.out.println(answer.toString().trim());
		
		
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