import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int c;

    static int[] homes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        homes = new int[n];

        for (int i = 0; i < n; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);

        int start = 1;
        int end = homes[n - 1] - homes[0] + 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (checkRouterCount(mid) < c) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }

    private static int checkRouterCount(int distance) {
        int count = 1;
        int lastLocation = homes[0];

        for (int i = 1; i < n; i++) {
            if (homes[i] - lastLocation >= distance) {
                ++count;
                lastLocation = homes[i];
            }
        }

        return count;
    }

}