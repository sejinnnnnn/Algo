import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if (n < 5) {
            System.out.println(0);
            return;
        }

        int[] twoCounts = new int[n + 1];
        int[] fiveCounts = new int[n + 1];

        int num = 4;
        twoCounts[2] = 1;
        while (num <= n) {
            twoCounts[num] = twoCounts[num / 2] + 1;
            num += 2;
        }

        num = 10;
        fiveCounts[5] = 1;
        while (num <= n) {
            fiveCounts[num] = fiveCounts[num / 5] + 1;
            num += 5;
        }

        int twoSum = 0;
        int fiveSum = 0;

        for (int i = 2; i <= n; i++) {
            twoSum += twoCounts[i];
            fiveSum += fiveCounts[i];
        }

        System.out.println(Math.min(twoSum, fiveSum));

    }

}