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
		
		int[] dp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		dp[0] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i < n; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			if (start == 0) {
				sb.append(dp[end] + "\n");
			} else {
				sb.append(dp[end] - dp[start - 1] + "\n");
			}
			
		}
		
		System.out.println(sb.toString().trim());
		
		
		
	}

}
