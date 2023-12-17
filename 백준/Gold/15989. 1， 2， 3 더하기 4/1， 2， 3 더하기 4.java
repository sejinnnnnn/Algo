import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        int prev = 3;

        for (int t = 0; t < testCase; t++) {
            int N = Integer.parseInt(br.readLine());
            if (N > prev) {
                for (int i = prev + 1; i <= N; i++) {
                    dp[i][1] = dp[i - 1][1];
                    dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
                    dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
                }
                prev = N;
            }
            answer.append(String.format("%d\n", dp[N][1] + dp[N][2] + dp[N][3]));


        }

        System.out.println(answer.toString().trim());

    }

}