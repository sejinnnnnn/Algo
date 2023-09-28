import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D6. 사람 네트워크 2
 * 플로이드-워셜 알고리즘을 통하여 한 정점에서 다른 모든 정점으로의 최소값 구하기
 * 본 문제는 가중치가 없었으나, 가중치 있는 경우에도 적용 가능
 * 플로이드-워셜의 시간복잡도는 O(n^3)이므로, 정점 개수 확인하기가 매우 중요함
 *
 * 메모리 : 96,608 KB, 시간 : 3,194 ms
 * @author 세진
 */
public class Solution {

    // 적당히 큰 값을 활용해 무한대를 표시하는 상수 선언 (무한대 = 연결되어 있지 않은 상태)
    final static int INF = 9999;

    public static void main(String[] args) throws Exception {

        // 입출력 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        // 테케 수 입력받기
        int testCase = Integer.parseInt(br.readLine());

        // 테케 반복
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // n 입력받아 dp배열 초기화
            int n = Integer.parseInt(st.nextToken());

            // 동적테이블의 (i, j) 값의 정의 : 정점 i로부터 j까지의 거리의 최소값
            int[][] dp = new int[n][n];

            // 테이블에 값 넣기 (만약 자기 자신이 아니고 0이라면 연결되어있지 않은 상태이므로 INF로 대입)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                    if (dp[i][j] == 0 && i != j) dp[i][j] = INF;
                }
            }

            // 플로이드 워셜 알고리즘 수행
            // i에서 j까지의 경로의 최소값을 k번째까지 고려된 경유지를 거치는 최소값 / 직빵으로 가는 값 중 최소값 선택
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    if (i == k) continue;
                    for (int j = 0; j < n; j++) {
                        if (j == k || j == i) continue;
                        dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                    }
                }
            }

            // CC 최소값 찾기
            int ccMin = Integer.MAX_VALUE;

            // 행의 값을 다 더한 뒤 최소값 갱신
            for (int i = 0; i < n; i++) {
                int currentCC = 0;
                for (int j = 0; j < n; j++) {
                    currentCC += dp[i][j];
                }

                ccMin = Math.min(ccMin, currentCC);
            }

            // 정답 문자열에 추가
            answer.append(String.format("#%d %d\n", t, ccMin));

        }

        // 정답 출력
        System.out.println(answer.toString().trim());

    }


}