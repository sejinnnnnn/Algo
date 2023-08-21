import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3109. 빵집
 * DFS로 0번째 열에서 col - 1번째 열까지 파이프로 잇는다
 * row가 1씩 증가하면서 경로를 찾기 때문에 탐색 우선순위로 대각선 위를 탐색하는 순서로 구현하였다
 * 또한, col - 1번째 열에 도달했다면 해당 row에서는 더 이상 탐색을 진행하지 않고 다음 row 탐색 시작
 * 메모리 : 42004KB, 시간 : 332ms
 * @author 세진
 *
 */
public class Main {
	
	// x좌표 조절할 dx 배열 (오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순)
	static int[] dx = { -1, 0, 1 };
	
	// 행 열 저장할 변수
	static int row;
	static int col;
	
	// 지도, 방문 배열 저장
	static char[][] map;
	static boolean[][] visited;
	
	// 가스관 몇 개 성공했는지 저장할 변수
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 한 줄 입력받아 공백으로 구분
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 구분된 문자열 int로 파싱하여 행, 열 변수에 저장
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		// 입력받은 행과 열 크기만큼 map, 방문배열 초기화
		map = new char[row][col];
		visited = new boolean[row][col];
		
		// 행 열만큼 반복하며 map의 i, j 번째 인덱스에 값 저장하기
		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		// 가스관 연결 시도를 0번째 행 부터 row-1번째 행까지 수행
		for (int i = 0; i < row; i++) {
			
			// 첫 시작점의 방문배열 true로 갱신 (안 해도 되긴 하다 ! 어차피 왼쪽으로 안 옴)
			visited[i][0] = true;
			
			// dfs 메서드 실행
			dfs(i, 0, answer);
			
		}
		
		// 정답 출력
		System.out.println(answer);
		
	}
	
	/**
	 * DFS로 주어진 map을 탐색하는 메서드
	 * col - 1 번째 열까지 도달하면 answer를 1 증가시키고, 만약 재귀 호출 종료 후의 answer 값이 내가 지금 가지고 있는 count 매개변수 값과 다르다면 (가스관 연결에 성공했다면) 그냥 return
	 * @param x 행 좌표
	 * @param y 열 좌표
	 * @param count dfs메서드가 최초 실행되었을 때의 answer 변수의 값
	 */
	private static void dfs(int x, int y, int count) {
		
//		System.out.println(x + ", " + y);
		
		// col - 1번째 열까지 도달했다면 (가스관 연결이 성공했다면)
		if (y == col - 1) {
			// 정답 1 증가 후 return
			answer++;
			return;
		}
		
		// dx 크기만큼 반복
		for (int i = 0; i < 3; i++) {
			
			// 다음 탐색할 x, y 좌표를 만든다 (y는 무조건 1만 더해지므로 dy 배열 따로 필요가 없음)
			int nextX = x + dx[i];
			int nextY = y + 1;
			
			// 다음 x, y 좌표가 정상 범위 내에 있으면서 map내의 해당 좌표가 건물이 아니고 방문도 하지 않았다면
			if (nextX >= 0 && nextX < row && nextY < col && map[nextX][nextY] != 'x' && !visited[nextX][nextY]) {
				// 다음 좌표의 방문 배열을 true로 갱신
				visited[nextX][nextY] = true;
				// 다음 x, y 좌표와 현재 매개변수 count 값을 가지고 재귀 호출
				dfs(nextX, nextY, count);
				// 재귀메서드 return 후 현재 answer값과 기존에 내가 매개변수로 받았던 count값이 다를 시 return
				if (answer != count) return;
			}
			
		}
		
	}
	

}