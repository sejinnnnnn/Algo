import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int n;
    static int l;
    static int r;
    static int[][] map;

    static int[][] area;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int nSquare = n * n;
        int day = 0;

        while (true) {

            // 국경 개방 + 인구 이동시키기
            int areas = openBoundary();
//            System.out.println(areas);

            // 만약 영역의 개수가 배열 전체 칸 개수와 같다면 (인구 이동이 발생하지 않는다면) 반복문 탈출
            if (areas == nSquare + 1) break;

            // 일수 + 1
            day++;

        }

        // 정답 출력
        System.out.println(day);

    }

    private static void movePeople(int areas) {

        for (int a = 1; a < areas; a++) {

            int curTotal = 0;
            ArrayList<int[]> curAreas = new ArrayList<>();

            // 해당 영역에 포함되는 나라를 구하기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (area[i][j] == a) {
                        curAreas.add(new int[] { i, j });
                        curTotal += map[i][j];
                    }
                }
            }

            // 영역의 개수가 1개라면 계산 필요 없으므로 continue
            if (curAreas.size() == 1) continue;

            int newPopulation = curTotal / curAreas.size();

            for (int[] pos : curAreas) {
                map[pos[0]][pos[1]] = newPopulation;
            }

        }


    }

    private static int openBoundary() {
        int areaCount = 1;
        area = new int[n][n];

        ArrayList<ArrayList<int[]>> areaPosList = new ArrayList<>();
        areaPosList.add(new ArrayList<>()); // 0번째 리스트는 쓰지않지만 인덱스를 맞추기 위해 요소 하나 추가하기

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] != 0) continue;

                areaPosList.add(new ArrayList<>());
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { i, j });
                area[i][j] = areaCount;
                areaPosList.get(areaCount).add(new int[] { i, j });

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nextX = current[0] + dx[k];
                        int nextY = current[1] + dy[k];

                        if (indexInRange(nextX, nextY) && area[nextX][nextY] == 0 && diffInRange(current[0], current[1], nextX, nextY)) {
                            area[nextX][nextY] = areaCount;
                            queue.offer(new int[] { nextX, nextY });
                            areaPosList.get(areaCount).add(new int[] { nextX, nextY });
                        }
                    }
                }
                areaCount++;
            }
        }

        // 영역 계산하기
        for (int i = 1; i < areaPosList.size(); i++) {
            ArrayList<int[]> current = areaPosList.get(i);

            if (current.size() == 1) continue;

            int areaTotal = 0;

            for (int[] pos : current) {
                areaTotal += map[pos[0]][pos[1]];
            }

            int newPopulation = areaTotal / current.size();

            for (int[] pos : current) {
                map[pos[0]][pos[1]] = newPopulation;
            }

        }

        return areaCount;
    }

    private static boolean diffInRange(int x1, int y1, int x2, int y2) {
        int diff = Math.abs(map[x1][y1] - map[x2][y2]);
        return diff >= l && diff <= r;
    }

    private static boolean indexInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}