import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int teamCount = Math.min(N / 2, M);
        int remainings = N - teamCount * 2 + M - teamCount;

        if (K == 0 || remainings >= K) {
            System.out.println(teamCount);
        } else {
            int answer = (int) Math.ceil(1.0 * (K - remainings) / 3);
            System.out.println(Math.max(0, teamCount - answer));
        }

    }

}