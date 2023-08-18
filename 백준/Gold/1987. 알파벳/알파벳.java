import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static int row;
	static int col;
	
	static char[][] alphabetMap;
	static boolean[][] visited;
	static boolean[] alphaVisited;
	static int max = 0;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		alphabetMap = new char[row][col];
		visited = new boolean[row][col];
		alphaVisited = new boolean[26];
		
		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				alphabetMap[i][j] = line.charAt(j);
			}
		}
		
		visited[0][0] = true;
		alphaVisited[alphabetMap[0][0] - 'A'] = true;
		
		dfs(0, 0, 1);
		
		System.out.println(max);
		
	}
	
	
	private static void dfs(int x, int y, int count) {
		
		for (int i = 0; i < dx.length; i++) {
			
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !visited[nextX][nextY] && !alphaVisited[alphabetMap[nextX][nextY] - 'A']) {
				
				alphaVisited[alphabetMap[nextX][nextY] - 'A'] = true;
				visited[nextX][nextY] = true;
				
				dfs(nextX, nextY, count + 1);
				
				visited[nextX][nextY] = false;
				alphaVisited[alphabetMap[nextX][nextY] - 'A'] = false;
				
			}
			
		}
		
		if (count > max) max = count;
		
		
	}
	

}