import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<String> nSet = new TreeSet<>();
        TreeSet<String> answerSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            nSet.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();

            if (nSet.contains(name)) {
                answerSet.add(name);
            }
        }

        answer.append(answerSet.size()).append("\n");

        for (String name : answerSet) {
            answer.append(name).append("\n");
        }

        System.out.println(answer.toString().trim());
    }

}