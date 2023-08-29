import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		if (n == 2 || n == 3) {
			System.out.println(1);
			return;
		}
		
		
		int[] dp = new int[n + 1];
		
		dp[2] = 1;
		dp[3] = 1;
		
		for (int i = 4; i <= n; i++) {
			dp[i] = Math.min(dp[i / 3] + (i % 3) + 1, Math.min(dp[i - 1] + 1, dp[i / 2] + (i % 2) + 1));
		}
		
		System.out.println(dp[n]);
		
	}
	
}