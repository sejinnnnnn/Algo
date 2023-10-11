import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int MOD = 1000000007;
	static long[] factorial = new long[4000001];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int m = Integer.parseInt(br.readLine());
		int nMax = 1;
		
		factorial[0] = 1;
		factorial[1] = 1;
		
		for (int t = 0; t < m; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			if (nMax < n) {
				
				for (int i = nMax + 1; i <= n; i++) {
					factorial[i] = factorial[i - 1] * i % MOD;
				}
				
				nMax = n;
			}
			
			long denominator = (factorial[n - r] * factorial[r]) % MOD;
			long result = (square(denominator, MOD - 2) % MOD * factorial[n]) % MOD;
			
			answer.append(String.format("%d\n", result));
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	private static long square(long value, int count) {
		
		if (count == 0) return 1;
		
		long result = square(value, count / 2) % MOD;
		
		if (count % 2 == 0) return (result * result) % MOD;
		else return (result * result) % MOD * value % MOD;
	}
	
	
}