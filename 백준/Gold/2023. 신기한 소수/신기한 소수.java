import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static String[] first = { "2", "3", "5", "7" };
	static String[] next = { "1", "3", "7", "9" };
	static StringBuilder answer;
	static String tab = "\n";
	
	static int n;
	static StringBuilder numbers;


	public static void main(String[] args) {
		
		answer = new StringBuilder();
		
		Scanner s = new Scanner(System.in);
		n = s.nextInt();

		numbers = new StringBuilder();
		
		generatePrime(0);
		
		System.out.println(answer.toString());
	}
	
	private static void generatePrime(int count) {
		
		if (count == 0) {
			for (int i = 0; i < 4; i++) {
				numbers.append(first[i]);
//				System.out.println(numbers.toString() + " " + count);
				generatePrime(count + 1);
				numbers.deleteCharAt(numbers.length() - 1);
			}
		} else if (count == n) {
			
			if (checkPrime(numbers.toString())) {
				answer.append(numbers);
				answer.append(tab);
			}
			
			return;
		} else {
			
			for (int i = 0; i < 4; i++) {
				numbers.append(next[i]);
//				System.out.println(numbers.toString() + " " + count);
				if (checkPrime(numbers.toString())) {
					generatePrime(count + 1);
					numbers.deleteCharAt(numbers.length() - 1);
				} else {
					numbers.deleteCharAt(numbers.length() - 1);
				}
			}
			
		}

	}
	
	private static boolean checkPrime(String numStr) {
		
		int num = Integer.parseInt(numStr);
		
		for (int i = 2; i < (int)Math.sqrt(num); i++) {
			
			if (num % i == 0) return false;
			
		}
		
		return true;
	}

}