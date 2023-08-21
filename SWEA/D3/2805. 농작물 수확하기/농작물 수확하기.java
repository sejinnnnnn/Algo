import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };		
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			int n = Integer.parseInt(br.readLine());
			
			int[][] farm = new int[n][n];
			boolean[][] visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				for (int j = 0; j < n; j++) {
					farm[i][j] = line.charAt(j) - '0';
				}
			}
			
			int total = 0;
			int half = (n - 1) / 2;
			int cols = 0;
			
			for (int i = 0; i < n; i++) {
				
				for (int j = half - cols; j <= half + cols; j++) {
					total += farm[i][j];
				}
				
				if (i < half) {
					cols += 1;
				} else {
					cols -= 1;
				}
				
			}
			
			answer.append(String.format("#%d %d\n", t, total));
			
			
		}
		
		System.out.println(answer.toString().trim());
		
	}

}