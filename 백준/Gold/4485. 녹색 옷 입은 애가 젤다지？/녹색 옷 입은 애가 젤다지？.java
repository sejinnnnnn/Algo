import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };
	
	static int problemCount = 1;
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	
	static int min;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		while (true) {
			
			n = Integer.parseInt(br.readLine());
			
			if (n == 0) break;
			
			map = new int[n][n];
			visited = new boolean[n][n];
			min = 0;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					min += map[i][j];
				}
			}
			
//			findMin(0, 0, map[0][0]);
			findMinBfs();
			
			answer.append(String.format("Problem %d: %d\n", problemCount++, min));
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	private static void findMinBfs() {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		int[][] visited = new int[n][n];
		
		for (int[] v : visited) Arrays.fill(v, 100000000);
		
		queue.offer(new int[] { 0, 0 });
		visited[0][0] = map[0][0];
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (isInRange(nextX, nextY) && visited[current[0]][current[1]] + map[nextX][nextY] < visited[nextX][nextY]) {
					queue.offer(new int[] { nextX, nextY });
					visited[nextX][nextY] = visited[current[0]][current[1]] + map[nextX][nextY];
				}
				
			}
			
		}
		
		min = Math.min(min, visited[n-1][n-1]);
		
	}
	
	
	private static void findMin(int x, int y, int count) {
		
		if (x == n - 1 && y == n - 1) {
			min = Math.min(min, count);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (isInRange(nextX, nextY) && !visited[nextX][nextY] && count + map[nextX][nextY] < min) {
				visited[nextX][nextY] = true;
				findMin(nextX, nextY, count + map[nextX][nextY]);
				visited[nextX][nextY] = false;
			}
		}
		
	}
	
	
	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	
}