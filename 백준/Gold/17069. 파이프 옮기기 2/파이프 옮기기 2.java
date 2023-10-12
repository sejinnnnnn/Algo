import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long answer = 0;
		
		int n = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				if (Integer.parseInt(st.nextToken()) == 0) map[i][j] = true;
			}
		}
		
		// n * n * 3 크기의 동적 테이블 생성
		// 3차원의 인덱스는 현재 모양으로 올 수 있는 값을 나타냄 (0 : 옆, 1 : 대각선, 2 : 밑)
		long[][][] dp = new long[n + 1][n + 1][3];
		
		dp[1][2][0] = 1;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 2; j <= n; j++) {
				
				if (i == 1 && j == 2) continue;
				
				if (map[i][j]) {
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
					
					if (map[i - 1][j] && map[i][j - 1]) {
						dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
					}
				}
				
			}
		}
		
//		for (int k = 0; k < 3; k++) {
//			for (int i = 1; i <= n; i++) {
//				for (int j = 1; j <= n; j++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		System.out.println((dp[n][n][0] + dp[n][n][1] + dp[n][n][2]));
		
		
	}

}