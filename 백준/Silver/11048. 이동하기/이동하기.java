import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11048. 이동하기
 * 해당 인덱스의 값을 받아와서 일단 자기 자신의 dp 배열 인덱스에 더해주고,
 * 오른쪽 / 아래 / 오른쪽아래 방향을 다음 두 가지 중 큰 값으로 갱신하였음
 * 1. 현재 자신의 dp배열 값 (그 방향으로 진행 시 가져갈 수 있는 현재까지의 선물의 최대값)
 * 2. 실제 오른쪽 / 아래 / 오른쪽아래 에 들어있는 dp배열 값 (다른 방향에서부터 온 값들 중 최댓값)
 * => 만약 현재까지의 값이 더 크다면 그 값이 최댓값이므로 갱신해주었음
 * 메모리 : 76,980KB, 시간 : 448ms
 * @author 세진
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n, m 공백으로 문자열 구분해서 저장하기
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// dp배열 생성
		int[][] dp = new int[n][m];
		
		// n*m만큼 반복
		for (int i = 0; i < n; i++) {
			// 공백으로 문자열 구분
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				// i,j 인덱스의 값 (문제 input)을 current 변수에 저장
				int current = Integer.parseInt(st.nextToken());
				
				// 현재 dp배열의 i,j 인덱스 값에 current를 더해준다 (현재 좌표를 방문했을 때 챙길 수 있는 선물의 값)
				dp[i][j] += current;
				
				// 만약 오른쪽 인덱스가 존재한다면, 현재 오른쪽 방향에 들어있는 값과 지금 i,j에 있는 dp배열 값을 비교해 최댓값 갱신
				if (j + 1 < m) dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j]);
				
				// 만약 아래 인덱스가 존재한다면, 현재 아래 방향에 들어있는 값과 지금 i,j에 있는 dp배열 값을 비교해 최댓값 갱신
				if (i + 1 < n) dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
				
				// 만약 대각선 인덱스가 존재한다면, 현재 대각선 방향에 들어있는 값과 지금 i,j에 있는 dp배열 값을 비교해 최댓값 갱신
				if (i + 1 < n && j + 1 < m) dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
				
			}
		}
		
		// n-1,m-1 번째 dp배열 출력 (정답)
		System.out.println(dp[n - 1][m - 1]);
		
	}

}