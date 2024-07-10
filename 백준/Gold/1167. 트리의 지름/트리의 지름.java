import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());

            while (true) {
                int arrival = Integer.parseInt(st.nextToken());
                if (arrival == -1) break;
                int weight = Integer.parseInt(st.nextToken());

                adjList.get(vertex).add(new int[] { arrival, weight });
            }
        }

        // 트리의 임의의 점에서 가장 먼 점 찾기 (다익스트라)
        int start = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N + 1];
        int[] weights = new int[N + 1];
        Arrays.fill(weights, INF);

        pq.add(new int[] { start, 0 });
        visited[start] = true;
        weights[start] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            ArrayList<int[]> currentEdges = adjList.get(current[0]);

            for (int[] next : currentEdges) {
                if (!visited[next[0]] && current[1] + next[1] < weights[next[0]]) {
                    visited[next[0]] = true;
                    weights[next[0]] = current[1] + next[1];

                    pq.add(new int[] { next[0], weights[next[0]] });
                }
            }

        }

        int maxValue = Integer.MIN_VALUE;
        int maxIdx = -1;

        for (int i = 1; i <= N; i++) {
            if (weights[i] > maxValue) {
                maxValue = weights[i];
                maxIdx = i;
            }
        }

        // 임의의 점에서 제일 먼 점은 트리의 길이 양 끝 점 중 하나이므로, 여기서 또 다익스트라로 제일 먼 점까지의 거리 찾기
        // 제일 먼 거리가 트리의 길이가 됨
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        visited = new boolean[N + 1];
        Arrays.fill(weights, INF);

        pq.add(new int[] { maxIdx, 0 });
        visited[maxIdx] = true;
        weights[maxIdx] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            ArrayList<int[]> currentEdges = adjList.get(current[0]);

            for (int[] next : currentEdges) {
                if (!visited[next[0]] && current[1] + next[1] < weights[next[0]]) {
                    visited[next[0]] = true;
                    weights[next[0]] = current[1] + next[1];
                    pq.add(new int[] { next[0], weights[next[0]] });
                }
            }

        }

        int answer = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, weights[i]);
        }

        System.out.println(answer);

    }

}