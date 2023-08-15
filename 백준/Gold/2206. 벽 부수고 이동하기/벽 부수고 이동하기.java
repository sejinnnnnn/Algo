import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		boolean[][][] visited = new boolean[n][m][2];
		
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		
		for (int i = 0; i < n; i++) {
			
			char[] ca = br.readLine().toCharArray();
			
			for (int j = 0; j < m; j++) {
				map[i][j] = ca[j] - '0';
			}
			
		}
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			
			Pair current = queue.poll();
//			visited[current.x][current.y][current.destroy] = true;
            
			// 만약 도착점이면
			if (current.x == n - 1 && current.y == m - 1) {
				visited[current.x][current.y][current.destroy] = true;
				queue.add(current);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				
				int nextX = current.x + dx[i];
				int nextY = current.y + dy[i];
				
				// Index 범위 벗어났을 땐 continue
				if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) { 
					continue;
				}
				
				// 방문했으면 (visited가 true이면) continue
				if (visited[nextX][nextY][current.destroy]) continue;
				
				// 벽이 아니면
				if (map[nextX][nextY] == 0) {
					if (current.destroy == 0) {
						visited[nextX][nextY][1] = true;
					}
					visited[nextX][nextY][current.destroy] = true;
					queue.add(new Pair(nextX, nextY, current.count + 1, current.destroy));
					continue;
				}
				
				// 벽이면
				if (map[nextX][nextY] == 1) {
					
					// 벽뚫 썼으면 continue, 안 썼으면 destroy true로 바꾸고 queue에 add (벽 뚫고 감)
					if (current.destroy == 1) continue;
					else {
						visited[nextX][nextY][current.destroy] = true;
						queue.add(new Pair(nextX, nextY, current.count + 1, 1));
						continue;
					}
					
				}
				
			}
			
		}
		
		if (!visited[n - 1][m - 1][0] && !visited[n - 1][m - 1][1]) {
			System.out.println(-1);
		}
		else {
			int min = queue.poll().count;
			
			while (!queue.isEmpty()) {
				int nextCount = queue.poll().count;
				if (nextCount < min) {
					min = nextCount;
				}
			}
			
			System.out.println(min);
		}
		
		
		
	}

}

class Pair {
	
	int x;
	int y;
	int count;
	int destroy;
	
	Pair(int x, int y, int count, int destroy) {
		this.x = x;
		this.y = y;
		this.count = count;
		this.destroy = destroy;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")" + " " + count + " " + destroy;
	}
	
}




