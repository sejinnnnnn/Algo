import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if (N == 1 || N == 2) System.out.println(1);
        else {
            long[][] dp = new long[N + 1][2]; // 1차원은 자리 숫자, 2차원은 끝자리

            dp[1][1] = 1;
            dp[2][0] = 1;

            for (int i = 3; i <= N; i++) {
                dp[i][0] = dp[i - 1][0] + dp[i - 1][1]; // 바로 전 자리숫자 0, 1로 끝나는 개수 (0 추가하기)
                dp[i][1] = dp[i - 1][0]; // 바로 전 자리숫자 0으로 끝나는 개수 (1 추가하기)
            }

            System.out.println((dp[N][0] + dp[N][1]));

        }

    }

}