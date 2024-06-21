import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] queue = new int[2000001];
    static int leftIdx = 0;
    static int rightIdx = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if (cmd.equals("push")) {
                push(Integer.parseInt(st.nextToken()));
            } else if (cmd.equals("pop")) {
                answer.append(pop()).append("\n");
            } else if (cmd.equals("size")) {
                answer.append(size()).append("\n");
            } else if (cmd.equals("empty")) {
                answer.append(empty()).append("\n");
            } else if (cmd.equals("front")) {
                answer.append(front()).append("\n");
            } else if (cmd.equals("back")) {
                answer.append(back()).append("\n");
            }
        }

        System.out.println(answer.toString().trim());

    }

    private static void push(int x) {
        queue[rightIdx++] = x;
    }

    private static int pop() {
        if (size() == 0) return -1;

        return queue[leftIdx++];
    }

    private static int size() {
        return rightIdx - leftIdx;
    }

    private static int empty() {
        if (size() == 0) return 1;
        else return 0;
    }

    private static int front() {
        if (size() == 0) return -1;
        return queue[leftIdx];
    }

    private static int back() {
        if (size() == 0) return -1;
        return queue[rightIdx - 1];
    }

}