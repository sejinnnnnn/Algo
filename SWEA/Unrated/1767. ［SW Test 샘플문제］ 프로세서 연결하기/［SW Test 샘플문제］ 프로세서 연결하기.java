import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    // 배열 크기 저장
    static int n;

    // 정보 저장 2차원 배열
    static int[][] map;

    // 코어 좌표 저장 리스트
    static ArrayList<int[]> cores;

    // 코어 값의 최댓값
    static int coreMax;
    // 길이의 최소값
    static int lengthMin;

    public static void main(String[] args) throws Exception {

        // 입출력 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        // 공백문자열 구분 객체 선언
        StringTokenizer st = null;

        // 테케 수 입력받기
        int testCase = Integer.parseInt(br.readLine());

        // 테케만큼 반복
        for (int t = 1; t <= testCase; t++) {

            // 배열 크기 n 입력받기
            n = Integer.parseInt(br.readLine());

            // 코어 최댓값, 길이 최소값 초기화
            coreMax = 0;
            lengthMin = Integer.MAX_VALUE;

            // n*n 만큼 map 배열 초기화
            map = new int[n][n];

            // 코어 저장 리스트 초기화
            cores = new ArrayList<>();

            // n * n번 반복
            for (int i = 0; i < n; i++) {
                // 공백으로 문자열 구분
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    // 배열에 공백문자열 하나 씩 구분지어 int로 파싱하여 저장
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        // 전기 연결이 필요없는 core라면 (벽에 붙어있는 core라면)
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                            map[i][j] = 3; // 전선이 아닌 벽으로 처리하기 위해 3 대입
                        } else cores.add(new int[] { i, j }); // 전선 연결 필요하다면 cores에 add
                    }
                }
            }

            // 0번째 코어부터 완전 탐색 실행
            checkCores(0, 0, 0);

            // 정답문자열에 추가
            answer.append(String.format("#%d %d\n", t, lengthMin));
        }

        // 정답 출력
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

        // 기저조건 : index 매개변수가 core의 개수만큼 찼다면
        if (index == cores.size()) {

            // 만약 코어 연결 개수가 현재까지의 코어 연갤 최댓값보다 크다면
            if (connectedCount > coreMax) {
                // 코어 최댓값을 현재의 코어 연결 값으로 갱신
                coreMax = connectedCount;
                // 길이 최소값을 현재의 길이로 갱신
                lengthMin = lineLength;
            }
            else if (connectedCount == coreMax) { // 만약 코어 최댓값과 현재까지 연결된 코어의 개수가 같다면
                // 현재 저장된 최소값과 지금 메서드의 매개변수 값과 비교하여 작은 값을 대입
                lengthMin = Math.min(lengthMin, lineLength);
            }

            // 현재 메서드 종료
            return;
        }

        // 인덱스 번째의 코어 좌표를 가져와 저장하기
        int[] currentCore = cores.get(index);

        // 방향 4개 만큼 반복
        for (int i = 0; i < 4; i++) {
            // 구현한 canConnect 메서드 반환 값이 true라면 (해당 방향으로 연결이 가능하다면)
            if (canConnect(currentCore[0], currentCore[1], i)) {
                // 새로 연결할 전선 개수를 connectPower 메서드의 반환값으로 받아오기
                int newLine = connectPower(currentCore[0], currentCore[1], i);
                // 다음 인덱스 번째의 메서드를 (연결 코어 수 + 1), (현재 길이 + 새 길이) 의 매개변수를 넘겨서 재귀 호출
                checkCores(index + 1, connectedCount + 1, lineLength + newLine);
                // 재귀호출한 메서드가 종료되었으면 다른 경로 탐색을 위해 다시 전선을 교체함
                disconnectPower(currentCore[0], currentCore[1], i);
            }
        }

        // 다음 인덱스 번째의 메서드를 연결코어수, 현재길이 그대로 호출 (현재 코어를 연결하지 않는 경우)
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

    /**
     * 주어진 행, 열 좌표를 기준으로 방향 정보에 해당하는 방향으로 map을 2로 갱신하고, 갱신한 길이 만큼 반환한다
     * @param x 기준 행 좌표
     * @param y 기준 열 좌표
     * @param direction 방향 정보
     * @return 갱신한 길이 수
     */
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

    /**
     * connectPower 메서드로 2로 갱신된 map을 다시 0으로 갱신 (전선을 없애기)
     * @param x 기준 행 좌표
     * @param y 기준 열 좌표
     * @param direction 방향 정보
     */
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