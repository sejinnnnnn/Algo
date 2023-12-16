import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[100001];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dp[N] = 1;
        queue.offer(N);

        while (true) {
            int current = queue.poll();
            if (current == K) {
                System.out.println((dp[K] - 1));
                break;
            }

            int mul2 = current;
            int diff = Math.abs(K - current);
            while (mul2 * 2 <= 100000) {
                mul2 *= 2;
//                System.out.println(mul2 + " " + diff + " " + Math.abs(K - mul2));
                if (diff > Math.abs(K - mul2)) {
                    dp[mul2] = dp[current];
                    queue.offer(mul2);
                    diff = Math.abs(K - mul2);
                }
                else break;
            }

//            if (current * 2 <= 100000 && dp[current * 2] == 0) {
//                dp[current * 2] = dp[current];
//                queue.offer(current * 2);
//            }
            if (current + 1 <= 100000 && dp[current + 1] == 0) {
                dp[current + 1] = dp[current] + 1;
                queue.offer(current + 1);
            }
            if (current - 1 >= 0 && dp[current - 1] == 0) {
                dp[current - 1] = dp[current] + 1;
                queue.offer(current - 1);
            }

//            System.out.println(queue);

        }



    }

}