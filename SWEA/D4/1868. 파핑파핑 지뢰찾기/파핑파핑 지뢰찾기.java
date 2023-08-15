import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {

    static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
    static int[][] map;
    static boolean[][] counted;
    static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {

            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            counted = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                String nextLine = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = nextLine.charAt(j) == '*' ? -1 : 0;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (map[i][j] == 0) {
                        int mineCount = 0;
                        for (int k = 0; k < 8; k++) {
                            int nextX = i + dx[k];
                            int nextY = j + dy[k];

                            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[nextX][nextY] == -1) {
                                mineCount++;
                            }

                        }

                        map[i][j] = mineCount;

                    }

                }
            }

            int click = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0 && !counted[i][j]) {
                        bfs(i, j);
                        click++;
//                        System.out.printf("%d, %d : %d\n", i, j, click);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != -1 && !counted[i][j]) {
                        click++;
//                        System.out.printf("%d, %d : %d\n", i, j, click);
                    }
                }
            }

            answer.append(String.format("#%d %d\n", t, click));

        }

        System.out.println(answer.toString().trim());

    }

    private static void bfs(int x, int y) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] { x, y });
        counted[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !counted[nextX][nextY]) {
                    counted[nextX][nextY] = true;
                    if (map[nextX][nextY] == 0) queue.add(new int[] { nextX, nextY });
                }
            }

        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(counted[i]));
//        }


    }

}