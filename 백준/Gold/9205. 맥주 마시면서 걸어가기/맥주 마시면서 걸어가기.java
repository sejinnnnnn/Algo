import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < testCase; t++) {
			
			int n = Integer.parseInt(br.readLine()) + 2;
			
			int[][] pos = new int[n][2]; // 집 + 편의점 + 목적지 까지의 좌표
			int[][] dp = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < i; j++) {
					dp[j][i] = dp[i][j] = Math.abs(pos[j][0] - pos[i][0]) + Math.abs(pos[j][1] - pos[i][1]);
				}
			}
			
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					if (i == k) continue;
					for (int j = 0; j < n; j++) {
						if (j == k || j == i) continue;
						
						int stopOver = dp[i][k] + dp[k][j];
						int direct = dp[i][j];
						
						if (stopOver <= 1000 || direct <= 1000) {
							dp[i][j] = 0;
						}
						
					}
				}
			}
			
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
			
			if (dp[0][n - 1] <= 1000) System.out.println("happy");
			else System.out.println("sad");
			
		}
		
	}

}