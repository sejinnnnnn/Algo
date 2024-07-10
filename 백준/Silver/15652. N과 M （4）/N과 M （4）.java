import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(new int[M], 0);

        System.out.println(answer.toString().trim());
    }

    private static void dfs(int[] sequence, int count) {

        if (count == M) {
            for (int i = 0; i < M; i++) {
                answer.append(sequence[i]).append(' ');
            }
            answer.append('\n');

            return;
        }

        if (count == 0) {
            for (int i = 1; i <= N; i++) {
                sequence[count] = i;
                dfs(sequence, count + 1);
            }
        } else {
            for (int i = sequence[count - 1]; i <= N; i++) {
                sequence[count] = i;
                dfs(sequence, count + 1);
            }
        }

    }

}