import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] knowsFact;

    static int[] parents;


    static int[][] partyQueries;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        knowsFact = new boolean[N + 1];

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        if (k == 0) {
            System.out.println(M);
            return;
        }

        for (int i = 0; i < k; i++) {
            knowsFact[Integer.parseInt(st.nextToken())] = true;
        }

        partyQueries = new int[M][];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());

            partyQueries[i] = new int[partyNum];
            partyQueries[i][0] = Integer.parseInt(st.nextToken());

            for (int j = 1; j < partyNum; j++) {
                partyQueries[i][j] = Integer.parseInt(st.nextToken());
                union(partyQueries[i][0], partyQueries[i][j]);
            }

        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                int iRoot = find(i);
                int jRoot = find(j);

                if (iRoot == jRoot) {
                    if (knowsFact[i] && !knowsFact[j]) knowsFact[j] = true;
                    else if (!knowsFact[i] && knowsFact[j]) knowsFact[i] = true;
                }
            }
        }

//        System.out.println(Arrays.toString(parents));
//        System.out.println(Arrays.toString(knowsFact));

        int answer = 0;

        nextParty:
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < partyQueries[i].length; j++) {
                if (knowsFact[partyQueries[i][j]]) continue nextParty;
            }
            ++answer;
        }

        System.out.println(answer);

    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

}