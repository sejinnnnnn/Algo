import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int rCount = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        int largerCount = 0;
        int sameCount = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int current = Integer.parseInt(st.nextToken());
            if (current > newScore) largerCount++;
            else if (current == newScore) {
                largerCount++;
                sameCount++;
            }

//            System.out.println(largerCount + " " + sameCount);

            if (largerCount >= rCount) {
                System.out.println(-1);
                return;
            }
        }
//        System.out.println(sameCount);

        System.out.println((++largerCount - sameCount));


    }

}