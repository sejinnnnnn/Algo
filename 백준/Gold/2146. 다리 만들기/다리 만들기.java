import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };
	
	static int n;
	static int[][] map;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		// 섬마다 1부터 서로 다른 일련번호를 부여하기
		int islandCount = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == -1) markIsland(i, j, islandCount++);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 만약 바다이면서, 해당 칸 근처에 육지가 있다면 (다리 만들기 시작조건에 부합하다면) 
				if (map[i][j] == 0) {
					int island = checkNearbyLand(i, j);
					if (island != -1) buildBridge(i, j, island);
				}
			}
		}
		
		System.out.println(min);
		
	}
	
	
	private static void buildBridge(int x, int y, int currentIsland) {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		
		queue.offer(new int[] { x, y, 0 });
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (checkRange(nextX, nextY)) {
					
					if (map[nextX][nextY] == 0 && !visited[nextX][nextY] && current[2] + 1 < min) {
						queue.offer(new int[] { nextX, nextY, current[2] + 1 });
						visited[nextX][nextY] = true;
					}
					
					// 다른 섬을 만났다면
					if (map[nextX][nextY] != 0 && map[nextX][nextY] != currentIsland) {
						min = Math.min(min, current[2] + 1);
//						System.out.println(nextX + " " + nextY + " : " + (current[2] + 1) + ", " + currentIsland + " -> " + map[nextX][nextY]);
					}
					
				}
				
			}
			
		}
		
	}
	
	
	private static void markIsland(int x, int y, int islandNum) {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] { x, y });
		map[x][y] = islandNum;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (checkRange(nextX, nextY) && map[nextX][nextY] == -1) {
					queue.offer(new int[] { nextX, nextY });
					map[nextX][nextY] = islandNum;
				}
			}
		}
		
	}
	
	
	private static int checkNearbyLand(int x, int y) {
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (checkRange(nextX, nextY) && map[nextX][nextY] != 0) return map[nextX][nextY];
		}
		
		return -1;
	}
	
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	
}