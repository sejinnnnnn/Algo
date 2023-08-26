import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[][] map;

    static ArrayList<int[]> cores;

    static int coreMax;
    static int lengthMin;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = null;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            coreMax = 0;
            lengthMin = Integer.MAX_VALUE;
            map = new int[n][n];
            cores = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        // 전기 연결이 필요없는 core라면
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                            map[i][j] = 3; // 전선이 아닌 벽으로 처리하기 위해 3 대입
                        } else cores.add(new int[] { i, j }); // 전선 연결 필요하다면 cores에 add
                    }
                }
            }

            // 0번째 코어부터 완전 탐색 실행
            checkCores(0, 0, 0);
            answer.append(String.format("#%d %d\n", t, lengthMin));
        }

        System.out.println(answer.toString().trim());
    }

    /**
     * 전원 연결 완전탐색
     * 0번째 인덱스부터 상, 하, 좌, 우를 살펴보면서 선 깔 수 있는지 체크하고 가능하다면 map을 2로 갱신한 뒤 재귀 호출
     * return 되었다면 이전에 갱신했던 값 다시 복구시키기
     * 마지막에는 이 index의 core를 연결하지 않는 재귀 메서드를 호출하기
     * @param index 살펴볼 core의 인덱스 값 (몇 번째인지)
     * @param connectedCount 현재까지 연결된 core의 개수
     * @param lineLength 현재까지 쓰인 전선의 길이
     */
    private static void checkCores(int index, int connectedCount, int lineLength) {

//        if (connectedCount < coreMax && lineLength >= lengthMin) return;

        if (index == cores.size()) {

            if (connectedCount > coreMax) {
                coreMax = connectedCount;
                lengthMin = lineLength;
            }
            else if (connectedCount == coreMax) {
                lengthMin = Math.min(lengthMin, lineLength);
            }

            return;
        }

        int[] currentCore = cores.get(index);

        for (int i = 0; i < 4; i++) {
            if (canConnect(currentCore[0], currentCore[1], i)) {
                int newLine = connectPower(currentCore[0], currentCore[1], i);
//                System.out.println(Arrays.toString(currentCore) + ", direction : " + i + ", length : " + newLine);
//                for (int[] m : map) System.out.println(Arrays.toString(m));
                checkCores(index + 1, connectedCount + 1, lineLength + newLine);
                disconnectPower(currentCore[0], currentCore[1], i);
            }
        }

        checkCores(index + 1, connectedCount, lineLength);

    }


    /**
     * 입력으로 들어오는 방향에 대해 map을 체크하며 연결할 수 있는지 없는지를 파악하는 메서드
     * @param direction 방향 (0 : 상, 1 : 하, 2 : 좌, 3 : 우)
     * @return 연결할 수 있으면 true, 못 한다면 false
     */
    private static boolean canConnect(int x, int y, int direction) {

        if (direction == 0) { // 상
            for (int i = x - 1; i >= 0; i--) {
                if (map[i][y] != 0) return false;
                if (i == 0) return true;
            }
        } else if (direction == 1) { // 하
            for (int i = x + 1; i < n; i++) {
                if (map[i][y] != 0) return false;
                if (i == n - 1) return true;
            }
        } else if (direction == 2) { // 좌
            for (int i = y - 1; i >= 0; i--) {
                if (map[x][i] != 0) return false;
                if (i == 0) return true;
            }
        } else if (direction == 3) { // 우
            for (int i = y + 1; i < n; i++) {
                if (map[x][i] != 0) return false;
                if (i == n - 1) return true;
            }
        }

        return false;
    }

    private static int connectPower(int x, int y, int direction) {

        if (direction == 0) { // 상
            for (int i = x - 1; i >= 0; i--) {
                map[i][y] = 2;
            }
            return x;
        } else if (direction == 1) { // 하
            for (int i = x + 1; i < n; i++) {
                map[i][y] = 2;
            }
            return n - (x + 1);
        } else if (direction == 2) { // 좌
            for (int i = y - 1; i >= 0; i--) {
                map[x][i] = 2;
            }
            return y;
        } else if (direction == 3) { // 우
            for (int i = y + 1; i < n; i++) {
                map[x][i] = 2;
            }
            return n - (y + 1);
        }

        return 0;
    }

    private static void disconnectPower(int x, int y, int direction) {
        if (direction == 0) { // 상
            for (int i = x - 1; i >= 0; i--) {
                map[i][y] = 0;
            }
        } else if (direction == 1) { // 하
            for (int i = x + 1; i < n; i++) {
                map[i][y] = 0;
            }
        } else if (direction == 2) { // 좌
            for (int i = y - 1; i >= 0; i--) {
                map[x][i] = 0;
            }
        } else if (direction == 3) { // 우
            for (int i = y + 1; i < n; i++) {
                map[x][i] = 0;
            }
        }
    }

}