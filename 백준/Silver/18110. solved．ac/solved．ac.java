import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int excludeCount = Math.round(n * 15 * 0.01f);

        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(scores);

        int avg = 0;

        for (int i = excludeCount; i < n - excludeCount; i++) {
            avg += scores[i];
        }

        System.out.println(Math.round(1.0f * avg / (n - 2 * excludeCount)));

    }

}