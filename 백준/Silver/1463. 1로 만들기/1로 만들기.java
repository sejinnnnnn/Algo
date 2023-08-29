import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1463. 1로 만들기
 * 초기 값 세팅 후 Bottom-up 방식으로 최소값을 구해주었음
 * 숫자마다 [(n-1 연산횟수) + 1, (n/3 몫의 연산횟수 + n%3) + 1, (n/2 몫 연산횟수 + n%2) + 1] 중 최소를 기록
 * 메모리 : 15,472KB, 시간 : 112ms
 * @author 세진
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// n이 1이면 0 출력 후 종료
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		// n이 2거나 3이면 나누기 연산 1번으로 1을 만들 수 있으므로 1번 출력 후 종료
		if (n == 2 || n == 3) {
			System.out.println(1);
			return;
		}
		
		// n+1 크기의 dp배열 생성
		int[] dp = new int[n + 1];
		
		// 초기값 세팅 (1번씩)
		dp[2] = 1;
		dp[3] = 1;
		
		// 4부터 n까지 반복
		for (int i = 4; i <= n; i++) {
			// 3으로 나눈 몫의 연산횟수 + 3으로 나눈 나머지 (나머지만큼 -1 연산) + 1 (3으로 나누기 연산)
			// 2로 나눈 몫의 연산횟수 + 2로 나눈 나머지 (나머지만큼 -1 연산) + 1 (2로 나누기 연산)
			// 1을 뺀 연산횟수 + 1 (-1 연산)
			// 위의 3개의 값 중 최소값을 i번째 배열에 넣음
			dp[i] = Math.min(dp[i / 3] + (i % 3) + 1, Math.min(dp[i - 1] + 1, dp[i / 2] + (i % 2) + 1));
		}
		
		// 정답 출력
		System.out.println(dp[n]);
		
	}
	
}