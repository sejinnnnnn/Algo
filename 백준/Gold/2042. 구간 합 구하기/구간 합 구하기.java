import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] arr;
    static long[] segmentTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        segmentTree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        buildTree(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            if (Integer.parseInt(st.nextToken()) == 1) {
                int where = Integer.parseInt(st.nextToken()) - 1;
                long value = Long.parseLong(st.nextToken());
                long diff = value - arr[where];
                arr[where] = value;
                updateTree(0, N - 1, 1, where, diff);
            } else {
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                answer.append(query(0, N - 1, 1, from, to)).append("\n");
            }

//            System.out.println(Arrays.toString(arr));
//            System.out.println(Arrays.toString(segmentTree));
        }

        System.out.println(answer.toString().trim());

    }

    private static long buildTree(int start, int end, int idx) {

        if (start == end) {
            segmentTree[idx] = arr[start];
        } else {
            int mid = (start + end) / 2;
            segmentTree[idx] = buildTree(start, mid, idx * 2) + buildTree(mid + 1, end, idx * 2 + 1);
        }

        return segmentTree[idx];
    }

    private static void updateTree(int start, int end, int idx, int where, long diff) {
        if (start > where || end < where) return;

        segmentTree[idx] += diff;
        if (start == end) return;

        int mid = (start + end) / 2;
        updateTree(start, mid, idx * 2, where, diff);
        updateTree(mid + 1, end, idx * 2 + 1, where, diff);
    }

    private static long query(int start, int end, int idx, int from, int to) {
        if (start > to || end < from) return 0;

        if (start >= from && end <= to) return segmentTree[idx];

        int mid = (start + end) / 2;
        return query(start, mid, idx * 2, from, to) + query(mid + 1, end, idx * 2 + 1, from, to);
    }

}