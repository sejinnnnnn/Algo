import java.util.Scanner;

public class Main {

	static int[] numbers;
	static int n;
	static int m;
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		
		numbers = new int[m + 1];
		makeComb(1, 1);
		
	}
	
	private static void makeComb(int count, int start) {
		
		if (count > m) {

			for (int i = 1 ; i <= m; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return ;
		}
		
		for (int i = start; i <= n; i++) {
			numbers[count] = i;
			makeComb(count + 1, i + 1);
		}
		
	}

}
