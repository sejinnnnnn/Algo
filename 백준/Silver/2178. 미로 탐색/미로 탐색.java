import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };		
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[row][col];
		boolean[][] visited = new boolean[row][col];
		
		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] { 0, 0, 1 });
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			if (current[0] == row - 1 && current[1] == col - 1) {
				System.out.println(current[2]);
				break;
			}
			
			
			for (int i = 0; i < dx.length; i++) {
				
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
					
					queue.add(new int[] { nextX, nextY, current[2] + 1 });
					visited[nextX][nextY] = true;
					
				}
				
				
			}
			
		}
		
		
		
	}

}