import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D4 7733. 치즈 도둑
 * 문제의 조건에 따라 현재 시간과 같은 값을 가지고 있는 요소를 지운 후, BFS로 영역 탐색
 * 메모리 : 79,676KB / 시간 : 341 ms 
 * @author 세진
 *
 */
public class Solution {
	
	// 4방 탐색을 위한 dx, dy 배열
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	// 치즈 크기
	static int n;
	
	// 치즈 맛을 저장할 2차원 배열
	static int[][] cheeze;

	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 공백문자열 구분 객체 생성
		StringTokenizer st = null;
		
		// 테케 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// 배열 크기 n 입력받기
			n = Integer.parseInt(br.readLine());
			
			// n*n 크기로 초기화
			cheeze = new int[n][n];
			
			// 맛 개수를 저장할 tasteCount 배열 (1 ~ 100 인덱스 사용)
			int[] tasteCount = new int[101];
			// 맛 최댓값 저장 변수
			int maxTaste = 1;
			
			// 값 입력받기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cheeze[i][j] = Integer.parseInt(st.nextToken());
					// 해당 맛 개수 저장 인덱스 + 1
					tasteCount[cheeze[i][j]]++;
					// 최댓값보다 지금 값이 크면 최댓값 갱신
					if (cheeze[i][j] > maxTaste) maxTaste = cheeze[i][j];
				}
			}
			
			// 최대 영역 저장 변수 (최초에 1임)
			int maxCount = 1;
			
			// 1초부터 100초까지 반복
			for (int time = 1; time <= 100; time++) {
				
				// 맛 개수가 0이라면 지울 요소가 없으므로 다음 time으로 넘어감
				if (tasteCount[time] == 0) continue;
//				if (eatCheeze(time) == 0) continue;
				
				// 치즈먹기 메서드 실행
				eatCheeze(time);
				
				// 현재 영역 개수를 countArea 메서드의 return 값으로 받아오기
				int currentArea = countArea();
				
//				if (currentArea == 0) break;
				
				// 최댓값 갱신
				if (currentArea > maxCount) maxCount = currentArea;
				
				// 만약 현재 시간이 최대 시간 값이라면 반복문 탈출 (더 이상 지울 요소가 없음)
				if (time == maxTaste) break;
				
			}
			
			// 최대 영역 개수 정답 문자열에 추가
			answer.append(String.format("#%d %d\n", t, maxCount));
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * 매개변수 값을 가진 배열 인덱스 값들을 0으로 바꾸는 메서드
	 * @param taste 0으로 갱신할 값
	 * @return 바꾼 총 개수
	 */
	private static int eatCheeze(int taste) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (cheeze[i][j] == taste) {
					cheeze[i][j] = 0;
					count++;
				}
			}
		}
		
		return count;
	}
	
	/**
	 * 영역 개수를 세는 메서드
	 * @return 현재 영역의 총 개수
	 */
	private static int countArea() {
		
		// 영역 개수 저장 변수
		int count = 0;
		
		// BFS 탐색 시 사용할 queue, 방문배열 생성
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		
		// n*n 번 탐색하면서
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				// 해당 배열 값이 0이 아니면서 방문하지 않았다면 (새로운 영역)
				if (cheeze[i][j] != 0 && !visited[i][j]) {
					
					// 영역 개수 + 1
					count++;
					
					// 현재 i, j 좌표를 queue에 넣고, 방문처리 하기
					queue.offerLast(new int[] { i, j });
					visited[i][j] = true;
					
					// queue가 빌 때 까지 반복 (그 영역을 다 탐색할 때 까지)
					while (!queue.isEmpty()) {
						// queue의 맨 앞의 값 꺼내오기
						int[] current = queue.pollFirst();
						
						// dx, dy 크기만큼 반복
						for (int k = 0; k < 4; k++) {
							// 다음 x, y 좌표 만들기
							int nextX = current[0] + dx[k];
							int nextY = current[1] + dy[k];
							// 다음 x, y 좌표가 정상 인덱스 범위 내에 있고, 방문하지 않았으며, 0이 아닌 값이라면
							if (isInRange(nextX, nextY) && !visited[nextX][nextY] && cheeze[nextX][nextY] != 0) {
								// 방문처리 후 queue에 넣기
								visited[nextX][nextY] = true;
								queue.offerLast(new int[] { nextX, nextY });
							}
						}
					}
					
				}
				
			}
			
		}
		
		// 계산된 영역 개수를 return
		return count;
	}
	
	/**
	 * 매개변수 x, y 좌표가 정상 인덱스 범위인지 확인하는 메서드
	 * @param x 행좌표
	 * @param y 열좌표
	 * @return 정상 인덱스 범위에 있으면 true, 아니면 false
	 */
	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	

}