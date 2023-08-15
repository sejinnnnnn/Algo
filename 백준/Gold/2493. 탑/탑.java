import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] towers = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		for (int i = 1; i <= n; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] answer = new int[n + 1];
		answer[1] = 0;
		
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] { towers[1], 1 });
		
		for (int i = 2; i <= n; i++) {
			
			while (!stack.isEmpty()) {
				int[] current = stack.pop();
				
//				System.out.println(stack);
				
				if (current[0] >= towers[i]) {
					answer[i] = current[1];
					stack.push(current);
					break;
				}
				
				if (current[1] == 1) {
					break;
				}
				
			}
			
			stack.push(new int[] { towers[i], i });
			
		}
		
		for (int i = 1; i <= n; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.println(sb.toString().trim());
		
	}

}