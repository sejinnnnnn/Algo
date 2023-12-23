import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        double sumDouble = 0.5 * (-1 + Math.sqrt(1 + 8 * N));
        int sumNum = (int) Math.ceil(sumDouble);

//        System.out.println(N + " " + sumDouble + " " + sumNum);

        int nth = N - sumNum * (sumNum - 1) / 2;
//        System.out.println(nth);

        if ((sumNum & 1) == 0) System.out.printf("%d/%d\n", nth, sumNum - nth + 1);
        else System.out.printf("%d/%d\n", sumNum - nth + 1, nth);

    }

}