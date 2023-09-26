import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static int row = 0;
	static int col = 0;
	
	static int[][] map;
	
	static int[][] currentMap;
	
	static ArrayList<int[]> empty;
	static ArrayList<int[]> virus;
	
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		
		empty = new ArrayList<>();
		virus = new ArrayList<>();
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 2) {
					virus.add(new int[] { i, j });
				} else if (map[i][j] == 0) {
					empty.add(new int[] { i, j });
				}
			}
		}
		
//		for (int i = 0; i < row; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// 조합을 만들 배열 생성 후 뒤에서부터 1로 채우기
		int[] combArr = new int[empty.size()];
		for (int i = 1; i <= 3; i++) {
			combArr[combArr.length - i] = 1;
		}
		
//		System.out.println(Arrays.toString(combArr));
		
		do {
			
			// 현재 scope에서 사용할 새 배열 복제해서 생성
			currentMap = new int[row][col];
			for (int i = 0; i < row; i++) {
				currentMap[i] = Arrays.copyOf(map[i], col);
			}
			
			// 조합으로 생성한 3개에 벽 세우기
			for (int i = 0; i < combArr.length; i++) {
				if (combArr[i] == 1) {
					int[] current = empty.get(i);
					currentMap[current[0]][current[1]] = 1;
				}
			}
			
			// 바이러스 BFS로 번식시키기
			spreadVirus();
			
//			for (int i = 0; i < row; i++) {
//				System.out.println(Arrays.toString(currentMap[i]));
//			}
			
			// 최종 안전지역 (0인 지역) 개수 구하고, 최소값 갱신
			int safeCount = 0;
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (currentMap[i][j] == 0) safeCount++;
				}
			}
			
			max = Math.max(max, safeCount);
			
			
		} while (npCombination(combArr, combArr.length));
		
		System.out.println(max);
		
		
	}
	
	private static void spreadVirus() {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < virus.size(); i++) {
			queue.offer(virus.get(i));
		}
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && currentMap[nextX][nextY] == 0) {
					currentMap[nextX][nextY] = 2;
					queue.add(new int[] { nextX, nextY });
				}
			}
			
		}
		
	}
	
	private static boolean npCombination(int[] arr, int size) {
		
		int i = size - 1;
		
		// 정상 값 찾기
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		if (i == 0) return false;
		
		int j = size - 1;
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		j = size - 1;
		while (i <= j) swap(arr, i++, j--);
		
		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}