import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		char[] stack = new char[100];
		int stackIdx = 0;
		
		nextLine:
		while (true) {
			char[] line = br.readLine().toCharArray();
			if (line.length == 1 && line[0] == '.') break;
			
			stackIdx = 0;
			
			for (char c : line) {
				
				if (c == '(') stack[stackIdx++] = '(';
				else if (c == '[') stack[stackIdx++] = '[';
				else if (c == ')') {
					if (stackIdx == 0 || stack[stackIdx - 1] != '(') {
						answer.append("no\n");
						continue nextLine;
					}
					stackIdx--;
				} else if (c == ']') {
					if (stackIdx == 0 || stack[stackIdx - 1] != '[') {
						answer.append("no\n");
						continue nextLine;
					}
					stackIdx--;
				}
				
			}
			
			if (stackIdx == 0) answer.append("yes\n");
			else answer.append("no\n");
			
		}
		
		System.out.println(answer.toString().trim());
		
	}

}