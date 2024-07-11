import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                queue.addLast(queue.pollFirst());
            }
            answer.add(queue.pollFirst());
        }

        StringBuffer answerStr = new StringBuffer();
        answerStr.append('<');

        for (int i = 0; i < n; i++) {
            answerStr.append(answer.get(i));
            if (i != n - 1) answerStr.append(", ");
        }
        answerStr.append('>');

        System.out.println(answerStr);
    }

}