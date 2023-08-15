import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		int squareNum = Math.min(n, m) / 2;
		
		for (int rotate = 0; rotate < r; rotate++) {
			
			int startX = 0;
			int startY = 0;
			
			while (startX < squareNum) {
				
				int temp = arr[startX][startY];
				
				for (int j = startY; j <= m - startY - 2; j++) {
//					System.out.println("위 : " + startX + ", " + j + " : " + arr[startX][j] + " -> " + arr[startX][j + 1]);
					arr[startX][j] = arr[startX][j + 1];
				}
				
				for (int i = startX; i <= n - startX - 2; i++) {
//					System.out.println("오른 : " + i + ", " + (m - startY - 1) + " : " + arr[i][m - startY - 1] + " -> " + arr[i + 1][m - startY - 1]);
					arr[i][m - startY - 1] = arr[i + 1][m - startY - 1];
				}
				
				for (int j = m - startY - 1; j >= startY + 1; j--) {
//					System.out.println("아래 : " + (n - startX - 1) + ", " + j + " : " + arr[n - startX - 1][j] + " -> " + arr[n - startX - 1][j - 1]);
					arr[n - startX - 1][j] = arr[n - startX - 1][j - 1];
				}
				
				for (int i = n - startX - 1; i >= startX + 1; i--) {
//					System.out.println("왼 : " + i + ", " + (m - startY - 1) + " : " + arr[i][m - startY - 1] + " -> " + arr[i - 1][m - startY - 1]);
					arr[i][startY] = arr[i - 1][startY];
				}
				
				arr[startX + 1][startY] = temp;
				
				startX++;
				startY++;
				
			}
			
		}
		

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer.append(arr[i][j]).append(" ");
			}
			answer.append("\n");
		}
		
		System.out.println(answer.toString());
		
		
	}

}