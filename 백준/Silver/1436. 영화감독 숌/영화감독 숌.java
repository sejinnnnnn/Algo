import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int num = 665;
        int count = 0;

        while (count < N) {
            ++num;

            int jari = (int) Math.log10(num) + 1;

            int[] numChar = new int[jari];
            int div = num;

            for (int i = jari - 1; i >= 0; i--) {
                numChar[i] = div % 10;
                div /= 10;
            }

            boolean contains = false;
            for (int i = 0; i <= jari - 3; i++) {
                if (numChar[i] == 6 && numChar[i + 1] == 6 && numChar[i + 2] == 6) {
                    contains = true;
                    break;
                }
            }

            if (contains) ++count;
        }

        System.out.println(num);

    }

}