import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int finish = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[100001];
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		if (start == finish) {
			System.out.println(0);
			return;
		}
		
		queue.add(new int[] { start - 1, 1 });
		queue.add(new int[] { start + 1, 1 });
		queue.add(new int[] { start * 2, 1 });
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
//			System.out.println(Arrays.toString(current));
			
			if (current[0] > 100000 || current[0] < 0) continue;
			visited[current[0]] = true;
			
			if (finish == current[0]) {
				System.out.println(current[1]);
				return;
			}
			
			if (current[0] * 2 <= 100000 && !visited[current[0] * 2]) queue.add(new int[] { current[0] * 2, current[1] + 1 });
			if (current[0] + 1 <= 100000 && !visited[current[0] + 1]) queue.add(new int[] { current[0] + 1, current[1] + 1 });
			if (current[0] - 1 >= 0 && !visited[current[0] - 1]) queue.add(new int[] { current[0] - 1, current[1] + 1 });
			
			
			
		}
		
		
	}

}