import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static int E;

    static ArrayList<ArrayList<Integer>> adjList;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        for (int t = 0; t < K; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                adjList.add(new ArrayList<>());
            }

            visited = new int[V + 1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList.get(from).add(to);
                adjList.get(to).add(from);
            }

            boolean result = false;
            for (int i = 1; i <= V; i++) {
                if (visited[i] == 0) {
                    result = bfs(i);
//                    System.out.println(Arrays.toString(visited));
                }
                if (!result) break;
            }

            if (result) answer.append("YES\n");
            else answer.append("NO\n");
        }

        System.out.println(answer.toString().trim());
        
    }

    private static boolean bfs(int vertex) {
        visited[vertex] = 1;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            ArrayList<Integer> edgeList = adjList.get(current);
            for (Integer next : edgeList) {
                if (visited[next] == 0) {
                    visited[next] = visited[current] * (-1);
                    queue.add(next);
                } else if (visited[next] == visited[current]) {
                    return false;
                }
            }

        }

        return true;
    }

}