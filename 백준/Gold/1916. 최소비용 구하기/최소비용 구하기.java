import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 200_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] adjustArr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(adjustArr[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjustArr[from][to] = Math.min(adjustArr[from][to], cost);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((arr -> arr[1])));

        int[] distanceArr = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(distanceArr, INF);

        distanceArr[start] = 0;

        pq.add(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            if (visited[current[0]]) continue;
            visited[current[0]] = true;

            for (int i = 1; i <= N; i++) {
                if (visited[i] || adjustArr[current[0]][i] == INF) continue;
                int nextDistance = current[1] + adjustArr[current[0]][i];

                if (nextDistance < distanceArr[i]) {
                    distanceArr[i] = nextDistance;
                    pq.add(new int[] { i, nextDistance });
                }
            }
        }

        System.out.println(distanceArr[end]);
    }

}