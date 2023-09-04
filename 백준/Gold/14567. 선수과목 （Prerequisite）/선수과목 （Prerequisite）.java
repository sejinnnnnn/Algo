import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
		int[] indegrees = new int[n + 1];
		
		for (int i = 0; i <= n; i++) {
			prerequisites.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int pre = Integer.parseInt(st.nextToken());
			int post = Integer.parseInt(st.nextToken());
			
			prerequisites.get(pre).add(post);
			indegrees[post]++;
		}
		
		int[] minArr = new int[n + 1];
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegrees[i] == 0) {
				queue.offerLast(new int[] { i, 1 });
				minArr[i] = 1;
			}
		}
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.pollFirst();
			
			for (int post : prerequisites.get(current[0])) {
				indegrees[post]--;
				if (indegrees[post] == 0) {
					queue.offerLast(new int[] { post, current[1] + 1 });
					minArr[post] = current[1] + 1;
				}
			}
			
		}
		
		for (int i = 1; i <= n; i++) {
			answer.append(String.format("%d ", minArr[i]));
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
}