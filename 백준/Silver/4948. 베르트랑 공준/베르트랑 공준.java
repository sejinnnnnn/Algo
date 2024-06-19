import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        boolean[] isPrime = new boolean[300001];
        Arrays.fill(isPrime, true);

        isPrime[1] = false;

        for (int i = 2; i <= 300001 / 2; i++) {
            if (isPrime[i]) {
                int num = i * 2;
                while (num <= 300000) {
                    isPrime[num] = false;
                    num += i;
                }
            }
        }

//        for (int i = 1; i <= 20; i++) {
//            System.out.print(isPrime[i] + " ");
//        }

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            int sum = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (isPrime[i]) ++sum;
            }

            answer.append(String.format("%d\n", sum));
        }

        System.out.println(answer.toString().trim());

    }

}