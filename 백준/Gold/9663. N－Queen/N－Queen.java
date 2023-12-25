import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] col;
    static int count;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        col = new int[N];
        count = 0;

        nQueen(0);
        System.out.println(count);

    }

    private static void nQueen(int row) {

        // 조건 체크
        if (row != 0 && !isAvailable(row - 1)) return;

        // 기저조건 : 모든 열에 Queen을 다 놓았다면 정답으로 체크 후 종료
        if (row == N) {
//            System.out.println(Arrays.toString(col));
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            col[row] = i;
            nQueen(row + 1);
        }

    }

    private static boolean isAvailable(int row) {
        for (int i = 0; i < row; i++) {
            if (col[i] == col[row] || row - i == Math.abs(col[row] - col[i])) return false;
        }
        return true;
    }

}