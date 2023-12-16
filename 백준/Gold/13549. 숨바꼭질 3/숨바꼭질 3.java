import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 백준 13549. 숨바꼭질 3
 * BFS를 이용해서 해결 / 기존의 숨바꼭질과 다르게 순간이동이 0초이기 때문에 한 지점이 주어지면 그 점의 2의 배수는 다 같은 값을 가짐
 * 갱신 후 그 점들을 다 queue에 넣으면 기존의 점과 엄청 거리가 먼 점들도 다 queue에 들어감 -> 시간 / 메모리 초과
 * 순간이동 (*2) 값이 목표와 가까운 정도를 계산해서 그 값을 기준으로 가장 K와 가까운 값까지만 순간이동을 실행
 *
 * 메모리 : 19,008 KB, 시간 : 136 ms
 */
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