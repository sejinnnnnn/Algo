import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            int[] sequences = new int[20];

            for (int i = 0; i < 20; i++) {
                sequences[i] = Integer.parseInt(st.nextToken());
            }

            LinkedList<Integer> line = new LinkedList<>();
            int backCount = 0;
            line.add(sequences[0]);

            for (int i = 1; i < 20; i++) {
                int curSize = line.size();
                for (int j = 0; j < line.size(); j++) {
                    int next = line.get(j);
                    if (next > sequences[i]) {
                        backCount += line.size() - j;
                        line.add(j, sequences[i]);
                        break;
                    }

                    if (j == line.size() - 1) {
                        line.add(sequences[i]);
                        break;
                    }

//                    System.out.println(line);

                }
            }

            answer.append(String.format("%d %d\n", t, backCount));

        }

        System.out.println(answer.toString().trim());

    }

}