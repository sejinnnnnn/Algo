import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k || k == 0) {
            System.out.println(1);
            return;
        }

        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        factorial[1] = 1;

        for (int i = 2; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        System.out.println(factorial[n] / (factorial[k] * factorial[n - k]));

    }

}