import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int count = 0;
	
	static int[] dx = { 0, 0, 1, 0 };
	static int[] dy = { 0, 1, -1, 1 };
	
	static int r, c;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		zSearch(0, 0, (int) Math.pow(2, n));
		
		
	}
	
	private static void zSearch(int startRow, int startCol, int size) {
		
		if (size > 2) {
			
			int half = size / 2;
			
			if (r < startRow + half && c < startCol + half) {
				zSearch(startRow, startCol, half);
			} else {
				count += half * half;
			}
			
			if (r < startRow + half && c >= startCol + half) {
				zSearch(startRow, startCol + half, half);
			} else {
				count += half * half;
			}
			
			if (r >= startRow + half && c < startCol + half) {
				zSearch(startRow + half, startCol, half);
			} else {
				count += half * half;
			}
			
			if (r >= startRow + half && c >= startCol + half) {
				zSearch(startRow + half, startCol + half, half);
			} else {
				count += half * half;
			}
			
		} else if (size == 2) {
			
			if (startRow <= r && startRow + 2 > r && startCol <= c && startCol + 2 > c) {
				
				int nextRow = startRow;
				int nextCol = startCol;
				
				for (int i = 0; i < 4; i++) {
					
					nextRow += dx[i];
					nextCol += dy[i];
					
//					System.out.printf("(%d, %d) : %d\n", nextRow, nextCol, count);
					
					if (nextRow == r && nextCol == c) {
						System.out.println(count);
						System.exit(0);
					}
					
					count++;
					
				}
				
			} else count += 4;
			
			
			
		}
		
	}

}