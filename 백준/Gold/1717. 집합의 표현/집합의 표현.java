import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			if (Integer.parseInt(st.nextToken()) == 0) {
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				int aFind = find(Integer.parseInt(st.nextToken()));
				int bFind = find(Integer.parseInt(st.nextToken()));
				if (aFind == bFind) answer.append("YES\n");
				else answer.append("NO\n");
			}
			
		}
		
		
		System.out.println(answer.toString().trim());
		
	}
	
	
	private static int find(int a) {
		if (a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 대표 원소가 같다면 같은 집합
		if (aRoot == bRoot) return false;
		
		// b를 a에 합치기
		parent[bRoot] = aRoot;
		
		return true;
	}

}