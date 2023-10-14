import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = { -1, 0, 0, 1 };
    static final int[] dy = { 0, -1, 1, 0 };

    static int n;
    static int[][] map;

    static int[] sharkPos;
    static int sharkSize;
    static int eatCount;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        sharkPos = new int[2];
        sharkSize = 2;
        eatCount = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkPos[0] = i;
                    sharkPos[1] = j;
                }
            }
        }

        int seconds = 0;

        while (true) {

            int[] nextFish = getFish();
            if (nextFish[0] == -1) break;



            map[sharkPos[0]][sharkPos[1]] = 0;

            sharkPos[0] = nextFish[0];
            sharkPos[1] = nextFish[1];
            eatCount++;

            if (eatCount == sharkSize) {
                eatCount = 0;
                sharkSize++;
            }

            map[sharkPos[0]][sharkPos[1]] = sharkSize;

            seconds += nextFish[2];

//            System.out.println(Arrays.toString(nextFish) + ", seconds : " + seconds);
//            for(int[] m : map) System.out.println(Arrays.toString(m));
//            System.out.println();

        }

        System.out.println(seconds);
    }

    private static int[] getFish() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        queue.offer(new int[] { sharkPos[0], sharkPos[1], 0 });
        visited[sharkPos[0]][sharkPos[1]] = true;

        ArrayList<int[]> minFishes = new ArrayList<>();
        int minDistance = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                if (isInRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] <= sharkSize) {

                    if (map[nextX][nextY] == 0 || map[nextX][nextY] == sharkSize) {
                        queue.offer(new int[] { nextX, nextY, current[2] + 1 });
                        visited[nextX][nextY] = true;
                    } else if (map[nextX][nextY] < sharkSize) {
                        if (minDistance == -1) {
                            minDistance = current[2] + 1;
                        }
                        if (current[2] + 1 == minDistance) {
                            minFishes.add(new int[] { nextX, nextY, current[2] + 1 });
                        }
                    }

                }

            }

        }

        if (minFishes.size() == 0) return new int[] { -1, -1, -1 };

        Collections.sort(minFishes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                else return Integer.compare(o1[1], o2[1]);
            }
        });

        return minFishes.get(0);

    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}