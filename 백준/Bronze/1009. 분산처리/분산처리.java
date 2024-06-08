import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] appeared = new boolean[10];
            ArrayList<Integer> sequence = new ArrayList<>();

            int num = 1;
            int count = 0;

            if (a % 10 == 1 || b == 1) {
                int val = a % 10;
                if (val == 0) answer.append(String.format("%d\n", 10));
                else answer.append(String.format("%d\n", val));
                continue;
            }

            while (true) {
//                System.out.println(num + " " + count);
                num = (num * a) % 10;
                count++;

                if (appeared[num] || count == b) break;

                appeared[num] = true;
                sequence.add(num);
            }

//            System.out.println(count + " " + sequence);
//            System.out.println(a + " " + b + " " + b % count);

            if (count == b) {
                if (num == 0) answer.append(String.format("%d\n", 10));
                else answer.append(String.format("%d\n", num));
            } else {
                int val = sequence.get((b - 1) % (count - 1));
                if (val == 0) answer.append(String.format("%d\n", 10));
                else answer.append(String.format("%d\n", val));
            }

        }

        System.out.println(answer.toString().trim());
    }

}