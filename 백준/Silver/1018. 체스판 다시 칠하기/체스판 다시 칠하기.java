import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;

    static char[][] map;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                findMin(i, j);
            }
        }

        System.out.println(min);

    }

    private static void findMin(int startX, int startY) {

        int wCount = 0;
        int bCount = 0;

        int firstModX = startX & 1;
        int firstModY = startY & 1;

        // 시작 칸이 흰색인 경우
        whiteStart:
        for (int i = startX; i < startX + 8; i++) {
            if ((i & 1) == firstModX) { // 0, 2, 4, 6 번째 행
                for (int j = startY; j < startY + 8; j++) {
                    if ((j & 1) == firstModY && map[i][j] != 'W') wCount++;
                    else if ((j & 1) != firstModY && map[i][j] != 'B') wCount++;
                }
            } else { // 1, 3, 5, 7 번째
                for (int j = startY; j < startY + 8; j++) {
                    if ((j & 1) == firstModY && map[i][j] != 'B') wCount++;
                    else if ((j & 1) != firstModY && map[i][j] != 'W') wCount++;
                }
            }
            if (wCount > min) break whiteStart;

            if (i == startX + 7) min = wCount;
        }

        // 시작 칸이 검은색인 경우
        blackStart:
        for (int i = startX; i < startX + 8; i++) {
            if ((i & 1) == firstModX) { // 0, 2, 4, 6 번째 행
                for (int j = startY; j < startY + 8; j++) {
                    if ((j & 1) == firstModY && map[i][j] != 'B') bCount++;
                    else if ((j & 1) != firstModY && map[i][j] != 'W') bCount++;
                }
            } else { // 1, 3, 5, 7 번째
                for (int j = startY; j < startY + 8; j++) {
                    if ((j & 1) == firstModY && map[i][j] != 'W') bCount++;
                    else if ((j & 1) != firstModY && map[i][j] != 'B') bCount++;
                }
            }
            if (bCount > min) break blackStart;

            if (i == startX + 7) min = bCount;
        }

//        System.out.println(wCount + " " + bCount);

    }

}