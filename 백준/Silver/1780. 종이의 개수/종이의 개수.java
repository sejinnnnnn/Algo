import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[] count;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		count = new int[3];
		
		int sameCheck = 0;
		boolean isAllSame = true;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 && j == 0) sameCheck = map[i][j];
				
				if (isAllSame && map[i][j] != sameCheck) isAllSame = false;
				
			}
		}
		
		if (n == 1 || isAllSame) {
			count[map[0][0] + 1] += 1;
		} else {
			divideAndConquer(0, 0, n);
		}
		
		System.out.printf("%d\n%d\n%d\n", count[0], count[1], count[2]);
		
		
		
	}
	
	private static void divideAndConquer(int rowStart, int colStart, int size) {
		
		int delta = size / 3;
		int row = rowStart;
		int col = colStart;
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				int startValue = map[row][col];
				boolean allSame = true;
				
				for (int i = row; i < row + delta; i++) {
					for (int j = col; j < col + delta; j++) {
						if (map[i][j] != startValue) {
							allSame = false;
							break;
						}
					}
				}
				
				if (!allSame) {
					divideAndConquer(row, col, delta);
				} else {
//					System.out.printf("(%d, %d) = %d : %d\n", row, col, startValue, delta * delta);
					count[startValue + 1] += 1;
				}
				col += delta;
			}
			row += delta;
			col = colStart;
		}
		
	}

}