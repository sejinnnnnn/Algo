import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] rgb = new int[n + 1][3]; // 0 : 빨강, 1 : 초록, 2 : 빨강
		int[][] dp = new int[n + 1][3];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
			
			if (i == 1) {
				dp[i][0] = rgb[i][0];
				dp[i][1] = rgb[i][1];
				dp[i][2] = rgb[i][2];
			} else {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
			}
			
		}
		
		System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
		
	}
	
}