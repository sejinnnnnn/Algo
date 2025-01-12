import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] requirements;
    static int maxRequirement;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        requirements = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            requirements[i] = Integer.parseInt(st.nextToken());
        }

        maxRequirement = Integer.parseInt(br.readLine());

        Arrays.sort(requirements);

        int start = 0;
        int end = requirements[n - 1] + 1;

        while (start < end) {
            int mid = (start + end) / 2;

//            System.out.println(start + " " + end + " " + mid);

            if (checkRequirement(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

//        System.out.println(start + " " + end);

        System.out.println(start - 1);
    }

    public static boolean checkRequirement(int r) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Math.min(requirements[i], r);
//            System.out.println(sum);
            if (sum > maxRequirement) return false;
        }

        return true;
    }

}