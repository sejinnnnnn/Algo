import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                answer.append(' ');
            }
            for (int j = 0; j < 2 * (i + 1) - 1; j++) {
                answer.append('*');
            }

            answer.append('\n');
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n - i - 1; j++) {
                answer.append(' ');
            }
            for (int j = 0; j < 2 * (i + 1) - 1; j++) {
                answer.append('*');
            }

            if (i != 0) answer.append('\n');
        }

        System.out.println(answer);
    }

}