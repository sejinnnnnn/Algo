import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int[] arr = new int[K];
        int idx = 0;

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                --idx;
                continue;
            }

            arr[idx++] = num;
        }

        int sum = 0;
        for (int i = 0; i < idx; i++) {
            sum += arr[i];
        }

        System.out.println(sum);
    }

}