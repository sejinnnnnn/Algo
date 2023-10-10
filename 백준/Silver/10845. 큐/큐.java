import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] queue = new int[10000];
	static int queueFront = 0;
	static int queueBack = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			
			case "push":
				push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				answer.append(pop()).append("\n");
				break;
			case "front":
				answer.append(front()).append("\n");
				break;
			case "back":
				answer.append(back()).append("\n");
				break;
			case "size":
				answer.append(size()).append("\n");
				break;
			case "empty":
				answer.append(empty()).append("\n");
				break;
			}
			
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	private static int size() {
		return queueBack - queueFront;
	}
	
	private static void push(int num) {
		queue[queueBack++] = num;
	}
	
	private static int pop() {
		if (size() == 0) return -1;
		else return queue[queueFront++];
	}
	
	private static int empty() {
		if (size() == 0) return 1;
		else return 0;
	}

	private static int back() {
		if (empty() == 1) return -1;
		else return queue[queueBack - 1];
	}

	private static int front() {
		if (empty() == 1) return -1;
		else return queue[queueFront];
	}

}