import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17069. 파이프 옮기기 2
 * 파이프 옮기기 1을 풀었어서, 아이디어 자체는 거의 비슷하게 풀었다
 * 3차원 동적 테이블을 이용해 모양을 구분해서 점화식을 세웠고, 주어진 방의 구조를 체크해서 이전으로부터 올 수 있는 값을 더해서 계산하여 주었음
 * 
 * 메모리 : 11,844 KB, 시간 : 88 ms
 * @author 세진
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 방 크기 n 입력받고 방 상태 정보 저장 배열 n + 1 크기로 초기화
		int n = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[n + 1][n + 1];
		
		// n * n * 3 크기의 동적 테이블 생성
		// 3차원의 인덱스는 현재 모양으로 올 수 있는 값을 나타냄 (0 : 옆, 1 : 대각선, 2 : 밑)
		long[][][] dp = new long[n + 1][n + 1][3];
		
		// 방 정보 입력받아 만약 0이라면 갈 수 있으므로 true로 설정해주기
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				if (Integer.parseInt(st.nextToken()) == 0) map[i][j] = true;
			}
		}
		
		// 처음 시작 위치 (1,2)에서 옆을 보고 있으므로 1,2,0 값에 1을 넣어주기
		dp[1][2][0] = 1;
		
		// 각 행의 1번째 열은 갈 수 없으므로 행은 1부터, 열은 2부터 시작해서 n까지 반복
		for (int i = 1; i <= n; i++) {
			for (int j = 2; j <= n; j++) {
				
				// 시작 값이 0으로 덮어씌워지지 않게 continue
				if (i == 1 && j == 2) continue;
				
				// i, j를 갈 수 있으면
				if (map[i][j]) {
					
					// 옆은 대각선, 옆 모양이 올 수 있으므로 이전 좌표의 옆, 대각선 값을 더해주기
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
					
					// 아래는 대각선, 아래 모양이 올 수 있으므로 이전 좌표의 아래, 대각선 값을 더해주기
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
					
					// 대각선 값을 추가로 구하기 위해 (i-1,j), (i, j-1) 값을 추가로 확인해서 비어있다면
					if (map[i - 1][j] && map[i][j - 1]) {
						// 대각선은 옆, 아래, 대각선 모양이 다 올 수 있으므로 더해주기
						dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
					}
				}
				
			}
		}
		
//		for (int k = 0; k < 3; k++) {
//			for (int i = 1; i <= n; i++) {
//				for (int j = 1; j <= n; j++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		// n,n 좌표의 모든 모양이 올 수 있는 경우를 더해서 정답 출력
		System.out.println((dp[n][n][0] + dp[n][n][1] + dp[n][n][2]));
		
		
	}

}