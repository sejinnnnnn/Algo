import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if (N == 1 || N == 2) {
            System.out.println(N);
            return;
        }

        long[] dp = new long[N + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] % 15746 + dp[i - 2] % 15746;
        }

        System.out.println(dp[N] % 15746);
    }

}