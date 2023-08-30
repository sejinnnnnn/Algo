import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] binomial = new int[m+1][n+1];
			
			for (int i = 0; i <= m; i++) {
				int end = Math.min(i, n);
				for (int j = 0; j <= end; j++) {
					if (j == 0 || i == j) binomial[i][j] = 1;
					else binomial[i][j] = binomial[i - 1][j - 1] + binomial[i - 1][j];
				}
//				System.out.println(Arrays.toString(binomial[i]));
			}
			
			answer.append(binomial[m][n] + "\n");
			
		}
		
		System.out.println(answer.toString().trim());
		
	}

}