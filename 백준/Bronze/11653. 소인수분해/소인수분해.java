import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if (N == 1) return;

        boolean[] isPrime = new boolean[N + 1];
        isPrime[2] = false;

        int mod = 2;

        while (mod <= N) {
            if (!isPrime[mod]) {
                int k = mod * 2;
                while (k <= N) {
                    isPrime[k] = true;
                    k += mod;
                }
            }
            mod++;
        }

//        System.out.println(Arrays.toString(isPrime));

        mod = 2;

        while (mod <= N) {
            if (!isPrime[mod] && N % mod == 0) {
                answer.append(String.format("%d\n", mod));
                N /= mod;
                mod = 2;
                continue;
            }
            mod++;
        }

        System.out.println(answer.toString().trim());

    }

}