import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };
	
	static int row;
	static int col;
	
	static char[][] map;
	static ArrayList<int[]> water;
	
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][];
		
		int[] start = new int[2];
		water = new ArrayList<>();
		
		for (int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (map[i][j] == '*') {
					water.add(new int[] { i, j });
				} else if (map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		
		// 고슴도치 BFS 시킬 변수들 준비하고 초기 값 세팅
		ArrayDeque<int[]> dQueue = new ArrayDeque<>();
		boolean[][] dVisited = new boolean[row][col];
		
		dQueue.offer(new int[] { start[0], start[1], 0 });
		dVisited[start[0]][start[1]] = true;
		
		// 물 BFS 시킬 변수들 준비하고 값 세팅
		ArrayDeque<int[]> wQueue = new ArrayDeque<>();
		boolean[][] wVisited = new boolean[row][col];
		
		for (int[] w : water) {
			wQueue.offer(w);
			wVisited[w[0]][w[1]] = true;
		}
		
		while (!dQueue.isEmpty()) {
			
			// 현재 물 Queue에 들어있는 물의 사이즈 담아서 저장하기
			int wCurrentSize = wQueue.size();
			
			// 현 시점 currentSize만큼 물 번식시키기
			for (int i = 0; i < wCurrentSize; i++) {
				
				int[] wCurrent = wQueue.poll();
				
				for (int j = 0; j < 4; j++) {
					int wNextX = wCurrent[0] + dx[j];
					int wNextY = wCurrent[1] + dy[j];
					
					if (isInRange(wNextX, wNextY) && map[wNextX][wNextY] == '.' && !wVisited[wNextX][wNextY]) {
						map[wNextX][wNextY] = '*';
						wVisited[wNextX][wNextY] = true;
						wQueue.offer(new int[] { wNextX, wNextY });
					}
					
				}
				
			}
			
			int dCurrentSize = dQueue.size();
			
			for (int i = 0; i < dCurrentSize; i++) {
				
				// 고슴도치 큐에서 꺼내오기
				int[] dCurrent = dQueue.poll();
				
				for (int j = 0; j < 4; j++) {
					
					int dNextX = dCurrent[0] + dx[j];
					int dNextY = dCurrent[1] + dy[j];
					
					if (isInRange(dNextX, dNextY)) {
						
						// 기저조건 : 만약 동굴에 도착했다면 정답 출력 후 프로그램 종료
						if (map[dNextX][dNextY] == 'D') {
							System.out.println(++dCurrent[2]);
							return;
						}
						
						if (map[dNextX][dNextY] == '.' && !dVisited[dNextX][dNextY]) {
							dVisited[dNextX][dNextY] = true;
							dQueue.offer(new int[] { dNextX, dNextY, dCurrent[2] + 1 });
						}
						
					}
					
				}
				
			}
			
			
		}
		
		System.out.println("KAKTUS");
		
	}
	
	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < row && y >= 0 && y < col;
	}

}