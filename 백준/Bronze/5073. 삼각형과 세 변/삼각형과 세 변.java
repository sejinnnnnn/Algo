import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] sides = new int[3];

            for (int i = 0; i < 3; i++) {
                sides[i] = Integer.parseInt(st.nextToken());
            }

            if (sides[0] == 0 && sides[1] == 0 && sides[2] == 0) break;

            Arrays.sort(sides);

            if (sides[2] >= sides[0] + sides[1]) answer.append("Invalid\n");
            else if (sides[0] == sides[1] && sides[1] == sides[2]) answer.append("Equilateral\n");
            else if (sides[0] == sides[1] || sides[1] == sides[2]) answer.append("Isosceles\n");
            else answer.append("Scalene\n");

        }

        System.out.println(answer.toString().trim());

    }

}