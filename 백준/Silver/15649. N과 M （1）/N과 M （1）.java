import java.util.Scanner;

public class Main {
	
	static int n;
	static int m;
	static StringBuilder sb;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		sb = new StringBuilder();
		
		n = s.nextInt();
		m = s.nextInt();
		
		s.close();
		
		makePerm(0, new int[m], new boolean[n]);
		
		System.out.println(sb.toString());
		
	}
	
	public static void makePerm(int count, int[] perm, boolean[] visited) {
		
		if (count == m) {
			
			for (int i = 0; i < m; i++) {
				sb.append(perm[i] + " ");
			}
			
			sb.append("\n");
			
		} else {
			
			for (int i = 0; i < visited.length; i++) {
				
				if (visited[i]) continue;
				
				perm[count] = i + 1;
				visited[i] = true;
				makePerm(count + 1, perm, visited);
				visited[i] = false;
				
			}
			
		}
		
	}

}
