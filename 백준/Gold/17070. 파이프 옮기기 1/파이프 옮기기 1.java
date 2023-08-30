import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		int[][][] count = new int[n][n][3]; // 0 : 가로, 1 : 세로, 2 : 대각선
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (i == 0 && j == 1) count[i][j][0] = 1;
				
				if (j + 1 < n && map[i][j + 1] == 0) {
					count[i][j + 1][0] += count[i][j][0] + count[i][j][2];
				}
				
				if (i + 1 < n && map[i + 1][j] == 0) {
					count[i + 1][j][1] += count[i][j][1] + count[i][j][2];
				}
				
				if (i + 1 < n && j + 1 < n && map[i][j + 1] == 0 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0) {
					count[i + 1][j + 1][2] += count[i][j][0] + count[i][j][1] + count[i][j][2];
				}
				
			}
		}
		
//		for (int k = 0; k < 3; k++) {
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(count[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		System.out.println((count[n - 1][n - 1][0] + count[n - 1][n - 1][1] + count[n - 1][n - 1][2]));
		
	}
	
}