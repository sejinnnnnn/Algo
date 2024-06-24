import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int minLength = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];

        while (left <= right) {
            if (sum >= S) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left++];
            } else {
                if (right == N - 1) break;
                sum += arr[++right];
            }
        }

        if (minLength == Integer.MAX_VALUE) minLength = 0;

        System.out.println(minLength);
    }

}