import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 15989. 1, 2, 3 더하기 (4)
 * 중복을 허용하지 않고 값을 계산해야 하기 때문에 -> 정렬된 값을 저장해야 함
 * 따라서, 2차원 동적 테이블을 만들어 1로 끝나는 값, 2로 끝나는 값, 3으로 끝나는 값을 따로 구분해서 저장하였다
 *
 * 메모리 : 13,556 KB, 시간 : 124 ms
 */
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