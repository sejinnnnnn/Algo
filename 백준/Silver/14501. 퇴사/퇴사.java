import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static int[] T;
    static int[] P;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(max);

    }

    private static void dfs(int day, int value) {

        if (day == N) {
            max = Math.max(max, value);
            return;
        }

        if (day + T[day] <= N) dfs(day + T[day], value + P[day]);
        dfs(day + 1, value);
    }

}