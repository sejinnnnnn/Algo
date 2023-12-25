import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int smaller = Math.min(A, B);
        int greater = Math.max(A, B);

        int count = 1;

        while (true) {

            if (count == smaller) {
                smaller = 1;
                break;
            }

//            System.out.println((1.0 * smaller / count) + " " + smaller / count + " " + count);
            if ((int) (1.0 * smaller / count) != smaller / count) {
                count++;
                continue;
            }

            int current = smaller / count;
            if (A % current == 0 && B % current == 0) {
                smaller = current;
                break;
            }

            count++;
        }

        System.out.println(smaller);
        System.out.println(A * B / smaller);

    }

}