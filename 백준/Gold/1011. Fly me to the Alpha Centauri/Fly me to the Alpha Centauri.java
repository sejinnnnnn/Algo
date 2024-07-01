import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = (Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken())) * -1;

            if (N <= 3) {
                answer.append(String.format("%d\n", N));
                continue;
            }

            int max = (int) Math.sqrt(N);

            if (max * 1.0 == Math.sqrt(N)) {
                answer.append(String.format("%d\n", max * 2 - 1));
            }
            else if (N <= max * max + max) {
                answer.append(String.format("%d\n", max * 2));
            }
            else {
                answer.append(String.format("%d\n", max * 2 + 1));
            }

        }

        System.out.println(answer.toString().trim());

    }

}