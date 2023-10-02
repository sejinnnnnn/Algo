import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };

    static int n;
    static int studentCount;
    static int[][] classroom;
    static int[] sequence;
    static int[][] preferences;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        studentCount = n * n;
        classroom = new int[n][n];
        sequence = new int[studentCount];
        preferences = new int[studentCount][4];

        for (int i = 0; i < n; i++) Arrays.fill(classroom[i], -1);

        for (int i = 0; i < studentCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sequence[i] = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 0; j < 4; j++) {
                preferences[sequence[i]][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        // 첫 번째 배정되는 학생은 항상 (1,1)에 앉히기
        classroom[1][1] = sequence[0];

        // 1번째 차례 학생부터 자리 배정하기
        for (int i = 1; i < studentCount; i++) {
            findPlaces(sequence[i]);
//            for (int[] c : classroom) System.out.println(Arrays.toString(c));
        }

        int answer = 0;

        // 자리 배정이 끝난 후 점수 부여
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int prefCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    if (isInRange(nextX, nextY)) {
                        for (int l = 0; l < 4; l++) {
                            if (preferences[classroom[i][j]][l] == classroom[nextX][nextY]) {
                                prefCount++;
                                break;
                            }
                        }
                    }
                }

                if (prefCount >= 1) answer += Math.pow(10, prefCount - 1);
            }

        }

        System.out.println(answer);

    }

    private static void findPlaces(int studentNo) {

        int adjustMax = -1;
        int emptyMax = -1;

        int seatX = -1;
        int seatY = -1;

        // 빈 자리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 빈 자리라면 해당 자리 기준 4방 탐색을 통해 선호 학생 따지기
                if (classroom[i][j] == -1) {
                    // 해당 자리 주변으로 선호학생이 몇 명 있는지 구하기
                    int curAdjust = countPreferenceSeat(studentNo, i, j);

                    // 만약 현재 선호학생이 최댓값보다 작다면 다른 자리 탐색
                    if (curAdjust < adjustMax) continue;

                    // 현재 기준 빈 자리 개수 구하기
                    int curEmpty = countEmptySeat(i, j);

                    // 선호학생 값이 최댓값과 같으면서 빈자리 개수가 같거나 작다면 다른 자리 탐색
                    if (curAdjust == adjustMax && curEmpty <= emptyMax) continue;

                    // 현재 값으로 최대 갱신
                    adjustMax = curAdjust;
                    emptyMax = curEmpty;
                    seatX = i;
                    seatY = j;
                }
            }
        }

        classroom[seatX][seatY] = studentNo;
    }

    private static int countPreferenceSeat(int studentNo, int x, int y) {
        int result = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isInRange(nextX, nextY) && classroom[nextX][nextY] != -1) {
                for (int j = 0; j < 4; j++) {
                    if (classroom[nextX][nextY] == preferences[studentNo][j]) {
                        result++;
                        break;
                    }
                }
            }

        }

        return result;
    }

    private static int countEmptySeat(int x, int y) {

        int result = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isInRange(nextX, nextY) && classroom[nextX][nextY] == -1) result++;

        }

        return result;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}