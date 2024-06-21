import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static ArrayList<int[]> blankList;

    static boolean isFinished = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        blankList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) blankList.add(new int[] { i, j });
            }
        }

//        System.out.println(blankList.size());

        addValue(0);

    }

    private static void addValue(int count) {

        if (isFinished) return;

        if (count == blankList.size()) {
            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.append(String.format("%d ", map[i][j]));
                }
                answer.append("\n");
            }

            System.out.println(answer.toString().trim());
            isFinished = true;

            return;
        }

        int[] current = blankList.get(count);

//        System.out.printf("addValue(%d) : %s\n", count, Arrays.toString(current));

        List<Integer> possibleList = checkPossibleValue(current[0], current[1]);

//        System.out.println(possibleList);

        if (possibleList.isEmpty()) return;

        for (Integer num : possibleList) {
            map[current[0]][current[1]] = num;
            addValue(count + 1);
            map[current[0]][current[1]] = 0;
        }

    }

    private static List<Integer> checkPossibleValue(int x, int y) {
        boolean[] isAppeared = new boolean[10];

        // 열 체크
        for (int i = 0; i < 9; i++) {
            if (i == y) continue;
            isAppeared[map[x][i]] = true;
        }

        // 행 체크
        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            isAppeared[map[i][y]] = true;
        }

        int rowStart = (x / 3) * 3;
        int colStart = (y / 3) * 3;

//        System.out.println(rowStart + " " + colStart);

        // 사각형 체크
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (i == x && j == y) continue;
                isAppeared[map[i][j]] = true;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            if (!isAppeared[i]) list.add(i);
        }

        return list;
    }

}