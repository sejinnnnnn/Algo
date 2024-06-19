import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] map;

    static boolean[][] visited;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i != 0 && j != 0) {
                    max = Math.max(max, map[i - 1][j - 1] + map[i][j - 1] + map[i - 1][j] + map[i][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // ㅗ
                if (j > 0 && j < M - 1 && i > 0) max = Math.max(max, map[i][j - 1] + map[i][j] + map[i - 1][j] + map[i][j + 1]);

                // ㅜ
                if (j > 0 && j < M - 1 && i < N - 1) max = Math.max(max, map[i][j - 1] + map[i][j] + map[i + 1][j] + map[i][j + 1]);

                // ㅓ
                if (i > 0 && i < N - 1 && j > 0) max = Math.max(max, map[i - 1][j] + map[i][j] + map[i][j - 1] + map[i + 1][j]);

                // ㅏ
                if (i > 0 && i < N - 1 && j < M - 1) max = Math.max(max, map[i - 1][j] + map[i][j] + map[i][j + 1] + map[i + 1][j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);

    }

    private static void dfs(int x, int y, int count, int sum) {

//        System.out.printf("dfs(x=%d, y=%d, count=%d, sum=%d)\n", x, y, count, sum);

        if (count == 4) {
//            if (max < sum) System.out.println(sum);
            max = Math.max(max, sum);
            return;
        }

        if (isInRange(x + 1, y) && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            dfs(x + 1, y, count + 1, sum + map[x + 1][y]);
            visited[x + 1][y] = false;
        }

        if (isInRange(x - 1, y) && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            dfs(x - 1, y, count + 1, sum + map[x - 1][y]);
            visited[x - 1][y] = false;
        }

        if (isInRange(x, y + 1) && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            dfs(x, y + 1, count + 1, sum + map[x][y + 1]);
            visited[x][y + 1] = false;
        }

        if (isInRange(x, y - 1) && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            dfs(x, y - 1, count + 1, sum + map[x][y - 1]);
            visited[x][y - 1] = false;
        }

    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}