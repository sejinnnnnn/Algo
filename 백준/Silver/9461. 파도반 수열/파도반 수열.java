import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        int maxN = 6;

        long[] dp = new long[101];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;

        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());

            if (n > maxN) {
                for (int i = maxN; i <= n; i++) {
                    dp[i] = dp[i - 1] + dp[i - 5];
                }

                maxN = n;
            }
            answer.append(String.format("%d\n", dp[n]));
        }

        System.out.println(answer.toString().trim());
    }

}