import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	
	public static StringBuilder sb;

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		sb = new StringBuilder();
		
		int n = s.nextInt();
		s.close();
		
		BigInteger answer = new BigInteger("2").pow(n).subtract(BigInteger.ONE);
//		Long answer = (long) Math.pow(2, n) - 1;
		
		sb.append(String.format("%s\n", answer.toString()));
//		System.out.println(answer);
		
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}

		System.out.print(sb.toString());

	}
	
	public static void hanoi(int n, int from, int by, int to) {

		if (n == 1) {
			sb.append(String.format("%d %d\n", from, to));
		} else {
			hanoi(n - 1, from, to, by);
			sb.append(String.format("%d %d\n", from, to));
			hanoi(n - 1, by, from, to);
		}
		
	}

}