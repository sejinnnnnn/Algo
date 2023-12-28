import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static int[] dxJump = { -1, -2, -2, -1, 1, 2, 2, 1 };
    static int[] dyJump = { -2, -1, 1, 2, 2, 1, -1, -2 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][col];
        boolean[][][] visited = new boolean[row][col][k + 1]; // 점프 차원만큼 만들어야 해

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (row == 1 && col == 1) {
            System.out.println(0);
            return;
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] { 0, 0, 0, 0 }); // 2번 인덱스가 지금까지 점프한 횟수
        visited[0][0][0] = true;

//		System.out.println(queue);

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

//			System.out.println(Arrays.toString(current));

            // 사방 탐색 후 이동
            for (int i = 0; i < 4; i++) {

                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                if (nextX == row - 1 && nextY == col - 1) {
                    System.out.println(current[3] + 1);
                    return;
                }

                if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && map[nextX][nextY] == 0 && !visited[nextX][nextY][current[2]]) {
                    visited[nextX][nextY][current[2]] = true;
                    queue.add(new int[] { nextX, nextY, current[2], current[3] + 1 });
                }

            }

            // 점프
            if (current[2] < k) {

                for (int i = 0; i < 8; i++) {

                    int nextX = current[0] + dxJump[i];
                    int nextY = current[1] + dyJump[i];

                    if (nextX == row - 1 && nextY == col - 1) {
                        System.out.println(current[3] + 1);
                        return;
                    }

                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && map[nextX][nextY] == 0 && !visited[nextX][nextY][current[2] + 1]) {
//						System.out.printf("점프 (%d, %d) : %d , 점프 남은 횟수 : %d\n", nextX, nextY, current[3], current[2] - 1);
                        visited[nextX][nextY][current[2] + 1] = true;
                        queue.add(new int[] { nextX, nextY, current[2] + 1, current[3] + 1 });
                    }

                }

            }

//			for (int l = 0; l < 2; l++) {
//				for (int i = 0; i < row; i++) {
//					for (int j = 0; j < col; j++) {
//						System.out.printf(visited[i][j][l] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}


        }

        System.out.println(-1);

    }

}