import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] names = new String[n];
        int[][] ranges = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            if (i == 0) ranges[i][0] = 0;
            else ranges[i][0] = ranges[i - 1][1] + 1;

            ranges[i][1] = Integer.parseInt(st.nextToken());
        }

        nextPower:
        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());

            int start = 0;
            int end = n - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (power < ranges[mid][0]) {
                    end = mid - 1;
                } else if (power > ranges[mid][1]) {
                    start = mid + 1;
                } else {
                    answer.append(names[mid] + "\n");
                    continue nextPower;
                }

//                System.out.println(start + " " + end);
            }

        }

        System.out.println(answer.toString().trim());

    }

}