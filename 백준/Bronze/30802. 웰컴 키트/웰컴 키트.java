import sun.font.FontRunIterator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] sizeCount = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            sizeCount[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int tSum = 0;
        int pSum = n / p;
        int pRemainder = n % p;

        for (int i = 0; i < 6; i++) {
            if (sizeCount[i] == 0) continue;
            if (sizeCount[i] % t == 0) tSum += sizeCount[i] / t;
            else tSum += sizeCount[i] / t + 1;
        }

        System.out.printf("%d\n%d %d", tSum, pSum, pRemainder);


    }

}