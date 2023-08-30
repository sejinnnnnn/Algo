import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1149. RGB거리
 * DP 배열을 선언하여 마지막이 빨강일 때, 초록일 때, 빨강일 때를 나누어서 저장
 * N번째 인덱스의 DP배열의 최소값을 정답으로 출력
 * 메모리 : 12,112KB, 시간 : 88ms
 * @author 세진
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// n 입력받기
		int n = Integer.parseInt(br.readLine());
		
		// DP 배열 생성 (0 : 빨강, 1 : 초록, 2 : 파랑)
		int[][] dp = new int[n + 1][3];
		
		for (int i = 1; i <= n; i++) {
			// 공백으로 문자열 구분
			st = new StringTokenizer(br.readLine());
			// 공백을 기준으로 해당 r, g, b 값을 가져오기
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (i == 1) { // i가 1일 땐 초기값으로 해당 r, g, b 값을 넣어주기
				dp[i][0] = r;
				dp[i][1] = g;
				dp[i][2] = b;
			} else { // 2보다 클 때에는 i-1번째 인덱스의 해당 색깔과 다른 두 가지 색의 최소값 + 해당 인덱스 색깔의 값을 저장 
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + r;
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + g;
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + b;
			}
			
		}
		
		// n의 3개의 요소 중 최소값 출력
		System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
		
	}
	
}