import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int[][] map;

    static int heightMax = Integer.MIN_VALUE;
    static int safetyAreaMax = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                heightMax = Math.max(heightMax, map[i][j]);
            }
        }

        for (int i = 0; i < heightMax; i++) {
            checkSafetyArea(i);
        }


        System.out.println(safetyAreaMax);
    }

    private static void checkSafetyArea(int height) {

        int areaCount = 0;
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > height && !visited[i][j]) {
                    ArrayDeque<int[]> queue = new ArrayDeque<>();

                    visited[i][j] = true;
                    queue.offer(new int[] { i, j });

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = current[0] + dx[k];
                            int ny = current[1] + dy[k];

                            if (isInRange(nx, ny) && map[nx][ny] > height && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                queue.offer(new int[] { nx, ny });
                            }
                        }

                    }

                    ++areaCount;
                }
            }
        }

        safetyAreaMax = Math.max(safetyAreaMax, areaCount);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}