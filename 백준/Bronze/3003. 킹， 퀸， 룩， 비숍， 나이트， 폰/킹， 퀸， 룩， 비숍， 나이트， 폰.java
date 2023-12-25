import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int[] arr = new int[6];

        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();

        answer.append(String.format("%d ", 1 - arr[0]));
        answer.append(String.format("%d ", 1 - arr[1]));
        answer.append(String.format("%d ", 2 - arr[2]));
        answer.append(String.format("%d ", 2 - arr[3]));
        answer.append(String.format("%d ", 2 - arr[4]));
        answer.append(String.format("%d", 8 - arr[5]));

        System.out.println(answer.toString());


    }

}