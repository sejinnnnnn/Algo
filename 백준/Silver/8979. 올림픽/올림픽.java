import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;

        int[][] medals = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            medals[idx][0] = Integer.parseInt(st.nextToken());
            medals[idx][1] = Integer.parseInt(st.nextToken());
            medals[idx][2] = Integer.parseInt(st.nextToken());
        }

        int[] kMedal = Arrays.copyOf(medals[k], 3);

        Arrays.sort(medals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return Integer.compare(o2[0], o1[0]);
                if (o1[1] != o2[1]) return Integer.compare(o2[1], o1[1]);
                return Integer.compare(o2[2], o1[2]);
            }
        });

        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(medals[i]));
            if (Arrays.equals(kMedal, medals[i])) {
                System.out.println((i + 1));
                break;
            }
        }



    }

}