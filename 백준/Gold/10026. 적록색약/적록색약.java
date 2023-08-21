import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static int n;
	
	static char[][] rgb;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		rgb = new char[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				rgb[i][j] = line.charAt(j);
			}
		}
		
		int normalAreaCount = bfs();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (rgb[i][j] == 'R') rgb[i][j] = 'G';
			}
		}
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);
		}
		
		int disabledAreaCount = bfs();
		
		
		
		System.out.println(normalAreaCount + " " + disabledAreaCount);
		
		
		
		
	}
	
	
	private static int bfs() {
		
		int count = 0;
		int startX = 0;
		int startY = 0;
		int visitCount = 0;
		
		while (visitCount < n * n) {
			
			ArrayDeque<int[]> queue = new ArrayDeque<>();
			
			queue.add(new int[] { startX, startY, rgb[startX][startY] });
			visited[startX][startY] = true;
			visitCount++;
			
			while (!queue.isEmpty()) {
				
				int[] current = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nextX = current[0] + dx[i];
					int nextY = current[1] + dy[i];
					
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextX][nextY] && current[2] == rgb[nextX][nextY]) {
						visited[nextX][nextY] = true;
						visitCount++;
						queue.add(new int[] { nextX, nextY, current[2] });
					}
					
				}
				
			}
			
			count++;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						startX = i;
						startY = j;
						break;
					}
				}
			}
			
		}
		
		return count;
		
	}
	

}