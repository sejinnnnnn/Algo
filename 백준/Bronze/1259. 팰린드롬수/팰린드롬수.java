import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final String YES = "yes\n";
    static final String NO = "no\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        nextNumber:
        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            int jari = (int) Math.log10(n) + 1;
            int[] arr = new int[jari / 2];

            if ((jari & 1) == 0) {
                for (int i = 0; i < jari / 2; i++) {
                    arr[i] = n % 10;
                    n /= 10;
                }
                for (int i = arr.length - 1; i >= 0; i--) {
                    if (arr[i] != n % 10) {
                        answer.append(NO);
                        continue nextNumber;
                    }
                    n /= 10;
                }

                answer.append(YES);
            } else {
                for (int i = 0; i < jari / 2; i++) {
                    arr[i] = n % 10;
                    n /= 10;
                }

                n /= 10;

                for (int i = arr.length - 1; i >= 0; i--) {
                    if (arr[i] != n % 10) {
                        answer.append(NO);
                        continue nextNumber;
                    }
                    n /= 10;
                }

                answer.append(YES);
            }

        }

        System.out.println(answer.toString().trim());

    }

}