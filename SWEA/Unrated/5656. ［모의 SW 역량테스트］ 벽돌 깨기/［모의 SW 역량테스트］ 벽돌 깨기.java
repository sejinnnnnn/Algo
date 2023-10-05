import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW 5656. 벽돌 깨기
 * 벽돌을 깰 수 있는 제약조건이 조금 있기 때문에 체크를 잘 해주기 (탐색하다가 위에 벽돌이 있으면 깨면 안 됨)
 * 깰 벽돌을 찾는 메서드, 벽돌을 깨는 메서드 분리해서 작성하였다 !!
 * 완전탐색 + 최댓값 갱신을 하였고, 처음에 계산해 놓았던 벽돌 총 개수에서 최댓값을 빼서 정답을 구했어요
 * @author 세진
 *
 */
public class Solution {
	
	// 4방탐색 dx, dy 배열
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };
	
	// 벽돌 깰 수 있는 수 n, 행 row, 열 col
	static int n;
	static int row;
	static int col;
	
	// 지도 저장 2차원 배열, 벽돌 개수 저장 변수
	static int[][] map;
	static int brickCount;
	
	// 최댓값 저장 변수
	static int max;
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			// 공백으로 문자열 구분해서 n, row, col 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			
			// 지도, 최댓값, 벽돌 총합 변수 초기화
			map = new int[row][col];
			max = Integer.MIN_VALUE;
			brickCount = 0;
			
			// 벽돌 지도 입력받고 만약 빈칸이 아니라면 총 개수 + 1
			for (int i = 0; i < row; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < col; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) brickCount++;
				}
			}
			
			// 벽돌 찾기 메서드 실행
			findBrick(map, 0, 0);
			
			// 갱신된 max 값을 총 개수에서 빼주어 정답 출력
			answer.append(String.format("#%d %d\n", t, brickCount - max));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * 깰 벽돌을 찾아서 완전탐색 하는 메서드
	 * @param currentMap 현재 지도
	 * @param reps 반복 횟수 (몇 번 깼는지)
	 * @param currentCount 현재까지의 벽돌 깬 수
	 */
	private static void findBrick(int[][] currentMap, int reps, int currentCount) {
		
		// 기저조건 : n번 반복했거나 모든 벽돌을 다 깨서 더 이상 깰 수 있는 벽돌이 없을 때
		if (reps == n || currentCount == brickCount) {
			
			// 최댓값 갱신
			if (currentCount > max) {
				max = currentCount;
//				System.out.println(currentCount + "/" + brickCount);
			}
			
			// 메서드 종료
			return ;
		}
		
		// 왼쪽 위부터 탐색시작
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				
				// 만약 0이 아니라면 (벽돌에 값이 있다면)
				if (currentMap[i][j] != 0) {
					
					// 맨 윗칸이 아니면서 현재 값 한 칸 위에 벽돌이 있다면 깰 수 없으므로 continue (맨 윗칸이라면 자동적으로 맨 위의 벽돌임)
					if (i != 0 && currentMap[i - 1][j] != 0) continue;
					
					// 다음 매개변수로 복제해줄 nextMap 초기화 후 현재 currentMap 복제
					int[][] nextMap = new int[row][];
					for (int k = 0; k < row; k++) {
						nextMap[k] = Arrays.copyOf(currentMap[k], col);
					}
					
					// 벽돌 깨서 깬 값을 저장해놓기
					int nextCount = breakBrick(nextMap, i, j, nextMap[i][j]);
					
					// 붕 떠있는 벽돌 떨어트리기
					dropBrick(nextMap);
					
					// 재귀 호출
					findBrick(nextMap, reps + 1, currentCount + nextCount);
				}
			}
		}
		
	}
	
	
	private static void printMap(int[][] currentMap) {
		for (int[] m : currentMap) System.out.println(Arrays.toString(m));
		System.out.println();
	}
	
	/**
	 * 붕 떠있는 벽돌을 떨어트리는 메서드
	 * @param currentMap 지도 2차원 배열
	 */
	private static void dropBrick(int[][] currentMap) {
		
		for (int j = 0; j < col; j++) {
			int bCount = 0;
			for (int i = row - 1; i >= 0; i--) {
				if (currentMap[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (currentMap[k][j] != 0) {
							currentMap[i][j] = currentMap[k][j];
							currentMap[k][j] = 0;
							i = row - ++bCount;
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 벽돌을 깨는 메서드
	 * @param currentMap 현재 갱신할 2차원 배열
	 * @param x 행좌표
	 * @param y 열좌표
	 * @param power 퍼지는 값
	 * @return 깬 벽돌의 개수
	 */
	private static int breakBrick(int[][] currentMap, int x, int y, int power) {
		
		int result = 1;
		currentMap[x][y] = 0;
		
		for (int d = 0; d < 4; d++) {
			for (int p = 1; p < power; p++) {
				int nextX = x + dx[d] * p;
				int nextY = y + dy[d] * p;
				
				if (!isInRange(nextX, nextY)) break;
				
				if (currentMap[nextX][nextY] != 0) {
					result += breakBrick(currentMap, nextX, nextY, currentMap[nextX][nextY]);
				}
			}
			
		}
		
		return result;
	}
	
	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < row && y >= 0 && y < col;
	}

}