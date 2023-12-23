import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int count = Integer.parseInt(br.readLine());

        for (int c = 0; c < count; c++) {

            int n = Integer.parseInt(br.readLine());

            if (n <= 2) answer.append(String.format("%d\n", n));
            else if (n == 3) answer.append("4\n");
            else {
                int[] dp = new int[n + 1];
                dp[1] = 1;
                dp[2] = 2;
                dp[3] = 4;

                for (int i = 4; i <= n; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                }
                answer.append(String.format("%d\n", dp[n]));
            }
        }

        System.out.println(answer.toString().trim());

    }

}