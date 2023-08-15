import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				
				if (i == 0 && j == 0) {
					dp[i][j] = Integer.parseInt(st.nextToken());
				} else if (i == 0 && j != 0) {
					dp[i][j] = Integer.parseInt(st.nextToken()) + dp[i][j - 1];
				} else if (i != 0 && j == 0) {
					dp[i][j] = Integer.parseInt(st.nextToken()) + dp[i - 1][j];
				} else {
					dp[i][j] = Integer.parseInt(st.nextToken()) + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
				}
				
			}

		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;

			if (x1 == 0 && y1 == 0) {
				sb.append(dp[x2][y2] + "\n");
			} else if (x1 == 0 && y1 != 0) {
				int answer = dp[x2][y2] - dp[x2][y1 - 1];
				sb.append(answer + "\n");
			} else if (x1 != 0 && y1 == 0) {
				int answer = dp[x2][y2] - dp[x1 - 1][y2];
				sb.append(answer + "\n");
			} else {
				int answer = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
				sb.append(answer + "\n");
			}
			
		}
		
		System.out.println(sb.toString());
		
	}

}