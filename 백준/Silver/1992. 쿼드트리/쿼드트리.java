import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] dots;
	static StringBuilder answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		dots = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				dots[i][j] = line.charAt(j) - '0';
			}
		}
		
		searchQuad(0, 0, n, 4);
		System.out.println(answer.toString());
		
		
	}
	
	
	private static void searchQuad(int rowStart, int colStart, int size, int where) {
		
		if (where == 0) answer.append("(");
		
		int total = 0;
		for (int i = rowStart; i < rowStart + size; i++) {
			for (int j = colStart; j < colStart + size; j++) {
				total += dots[i][j];
			}
		}
		
		if (total == 0) {
			answer.append("0");
		} else if (total == size * size) {
			answer.append("1");
		} else {
			int half = size / 2;
			searchQuad(rowStart, colStart, half, 0);
			searchQuad(rowStart, colStart + half, half, 1);
			searchQuad(rowStart + half, colStart, half, 2);
			searchQuad(rowStart + half, colStart + half, half, 3);
		}
		
		if (where == 3) answer.append(")");
		
	}
	
}