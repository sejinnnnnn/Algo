import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = { -1, 0, 1 };
	
	static int row;
	static int col;
	
	static char[][] map;
	static boolean[][] visited;
	
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][col];
		visited = new boolean[row][col];
		
		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < row; i++) {
			
			visited[i][0] = true;
			dfs(i, 0, answer);
			
		}
		
		System.out.println(answer);
		
	}
	
	
	private static void dfs(int x, int y, int count) {
		
//		System.out.println(x + ", " + y);
		
		if (y == col - 1) {
			answer++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nextX = x + dx[i];
			int nextY = y + 1;
			
			if (nextX >= 0 && nextX < row && nextY < col && map[nextX][nextY] != 'x' && !visited[nextX][nextY]) {
				visited[nextX][nextY] = true;
				dfs(nextX, nextY, count);
				if (answer != count) return;
			}
			
		}
		
	}
	

}