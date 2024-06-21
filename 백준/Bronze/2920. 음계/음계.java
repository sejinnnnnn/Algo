import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final String ascending = "ascending";
    private static final String descending = "descending";
    private static final String mixed = "mixed";

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int prev = Integer.parseInt(st.nextToken());

        if (prev == 8) {
            for (int i = 7; i > 0; i--) {
                if (i != Integer.parseInt(st.nextToken())) {
                    System.out.println(mixed);
                    return;
                }
            }

            System.out.println(descending);
        } else if (prev == 1) {
            for (int i = 2; i <= 8; i++) {
                if (i != Integer.parseInt(st.nextToken())) {
                    System.out.println(mixed);
                    return;
                }
            }

            System.out.println(ascending);
        } else System.out.println(mixed);

    }

}