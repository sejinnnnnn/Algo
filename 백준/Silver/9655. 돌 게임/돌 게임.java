import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if (N == 1 || N == 3) System.out.println("SK");
        else if (N == 2) System.out.println("CY");
        else {
            int[] dp = new int[N + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 1;

            for (int i = 4; i <= N; i++) {
                dp[i] = Math.min(dp[i - 3] + 1, dp[i - 1] + 1);
            }

            if ((dp[N] & 1) == 1) System.out.println("SK");
            else System.out.println("CY");

        }



    }
}