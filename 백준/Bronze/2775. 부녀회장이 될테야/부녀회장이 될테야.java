import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[k + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                arr[0][i] = i;
            }

            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int l = 1; l <= j; l++) {
                        arr[i][j] += arr[i - 1][l];
                    }
                }
            }

            answer.append(String.format("%d\n", arr[k][n]));

        }

        System.out.println(answer.toString().trim());
    }

}