import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        int[] cost = new int[n];
        int[] indegree = new int[n];
        int[] dp = new int[n];
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            paths.add(new ArrayList<>());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());

                if(num == -1)
                    break;

                paths.get(num - 1).add(i);
                indegree[i]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++)
            if(indegree[i] == 0)
                queue.offer(i);

        while(!queue.isEmpty()){
            int current = queue.poll();
            dp[current] += cost[current];

            for(int i = 0; i < paths.get(current).size(); i++){
                int next = paths.get(current).get(i);

                indegree[next]--;
                if(indegree[next] == 0)
                    queue.offer(next);

                dp[next] = Math.max(dp[next], dp[current]);
            }
        }

        for(int i = 0; i < n; i++)
            System.out.println(dp[i]);
    }

}