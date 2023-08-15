import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		nextCase:
		for (int test_case = 1; test_case <= t; test_case++) {
			
			char[] commands = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0) {
				
				br.readLine();
//				System.out.println(Arrays.toString(commands));
				for (int i = 0; i < commands.length; i++) {
					if (commands[i] == 'D') {
						System.out.println("error");
						continue nextCase;
					}
				}
				
				System.out.println("[]");
				continue nextCase;
			}
			
			int[] arr = new int[n];
			
			String inputStr = br.readLine();
			String arrStr = new String(Arrays.copyOfRange(inputStr.toCharArray(), 1, inputStr.length() - 1));
			String[] arrStrSplit = arrStr.split(",");
			
			for (int i = 0; i < n; i++) {
				
				arr[i] = Integer.parseInt(arrStrSplit[i]);
				
			}
			
			boolean isReverse = false;
			int leftStart = 0;
			int rightStart = n;
			
			for (int i = 0; i < commands.length; i++) {
				
				if (commands[i] == 'R') {
					isReverse = !isReverse;
					continue;
				}
				else if (commands[i] == 'D') {
					if (isReverse) {
						rightStart--;
					}
					else {
						leftStart++;
					}
					
					if (leftStart == rightStart) {
						
						if (i == commands.length - 1) {
							System.out.println("[]");
							continue nextCase;
						}
						
						System.out.println("error");
						continue nextCase;
					}
					
				}
				
			}
			
//			System.out.printf("left : %d, right : %d, isReverse : %b\n", leftStart, rightStart, isReverse);
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			
			if (leftStart == rightStart - 1) {
				sb.append(arr[leftStart]);
				sb.append("]");
				System.out.println(sb.toString());
				continue nextCase;
			}
			
			if (!isReverse) {
				
				for (int i = leftStart; i < rightStart; i++) {
					sb.append(arr[i]);
					
					if (i != rightStart - 1) {
						sb.append(",");
					}
					else {
						sb.append("]");
					}
				}
				
			}
			else {
				
				for (int i = rightStart - 1; i >= leftStart; i--) {
					sb.append(arr[i]);
					
					if (i != leftStart) {
						sb.append(",");
					}
					else {
						sb.append("]");
					}
				}
				
			}
			
			System.out.println(sb.toString());
			
			
			
		}
		
	}
}
