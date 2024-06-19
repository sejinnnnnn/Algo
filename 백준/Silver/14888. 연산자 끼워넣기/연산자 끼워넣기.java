import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] operationCount = new int[4];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            operationCount[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, numbers[0], operationCount);

        System.out.printf("%d\n%d", max, min);
    }

    private static void dfs(int count, int result, int[] operations) {

        if (count == N - 1) {
            min = Math.min(min, result);
            max = Math.max(max, result);

            return;
        }

        if (operations[0] > 0) {
            operations[0]--;
            dfs(count + 1, result + numbers[count + 1], operations);
            operations[0]++;
        }

        if (operations[1] > 0) {
            operations[1]--;
            dfs(count + 1, result - numbers[count + 1], operations);
            operations[1]++;
        }

        if (operations[2] > 0) {
            operations[2]--;
            dfs(count + 1, result * numbers[count + 1], operations);
            operations[2]++;
        }

        if (operations[3] > 0) {
            operations[3]--;
            if (result < 0 && numbers[count + 1] > 0) dfs(count + 1, (result * (-1) / numbers[count + 1]) * (-1), operations);
            else dfs(count + 1, result / numbers[count + 1], operations);
            operations[3]++;
        }


    }

}