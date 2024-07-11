import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final String right = "right\n";
    static final String wrong = "wrong\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 0 && b == 0 && c == 0) break;

            if (a > b && a > c) {
                if (Math.pow(a, 2) == Math.pow(b, 2) + Math.pow(c, 2)) answer.append(right);
                else answer.append(wrong);
            } else if (b > a && b > c) {
                if (Math.pow(b, 2) == Math.pow(a, 2) + Math.pow(c, 2)) answer.append(right);
                else answer.append(wrong);
            } else {
                if (Math.pow(c, 2) == Math.pow(a, 2) + Math.pow(b, 2)) answer.append(right);
                else answer.append(wrong);
            }

        }

        System.out.println(answer.toString().trim());
    }

}