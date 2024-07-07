import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dz = { 1, -1, 0, 0, 0, 0 };
    private static final int[] dx = { 0, 0, 1, -1, 0, 0 };
    private static final int[] dy = { 0, 0, 0, 0, 1, -1 };

    private static int[][][] map; // z를 1차원에 놓을까 3차원에 놓을까 ??
    private static int N, M, H;

    private static ArrayList<int[]> ripedList;
    private static int ripedCount;

    private static int totalCount;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];

        ripedList = new ArrayList<>();
        ripedCount = 0;

        totalCount = M * N * H;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());

                    if (map[i][j][k] == 1) {
                        ++ripedCount;
                        ripedList.add(new int[] { i, j, k });
                    } else if (map[i][j][k] == -1) --totalCount;
                }
            }
        }

        if (ripedCount == totalCount) {
            System.out.println(0);
            return;
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][N][M];
        int days = 0;

        for (int[] arr : ripedList) {
            queue.add(arr);
            visited[arr[0]][arr[1]][arr[2]] = true;
        }

        // 날짜 어케 체크할 거 ???
        while (true) {
            ++days;

            ArrayDeque<int[]> nextDayQueue = new ArrayDeque<>();

            for (int[] arr : queue) {
                for (int i = 0; i < 6; i++) {
                    int nz = arr[0] + dz[i];
                    int nx = arr[1] + dx[i];
                    int ny = arr[2] + dy[i];

                    if (isInRange(nx, ny, nz) && !visited[nz][nx][ny] && map[nz][nx][ny] != -1) {
                        visited[nz][nx][ny] = true;
                        nextDayQueue.add(new int[] { nz, nx, ny });
                        ++ripedCount;
                    }
                }
            }

            if (ripedCount == totalCount) {
                System.out.println(days);
                return;
            }

            if (nextDayQueue.isEmpty()) {
                System.out.println(-1);
                return;
            }

            queue = nextDayQueue;
        }


    }

    private static boolean isInRange(int x, int y, int z) {
        return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
    }


}