import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * D3 2805. 농작물 수확하기
 * 주어진 2차원 배열의 index를 조절하여 정해진 범위 (마름모)에 있는 값의 합을 출력하는 문제
 * 구현문제
 * 메모리 : 21888KB, 시간 : 127ms
 * @author user
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 테케 수 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// n 입력받기
			int n = Integer.parseInt(br.readLine());
			
			// 농장 2차원 배열 n * n 으로 초기화
			int[][] farm = new int[n][n];
			
			// n*n 번 반복하며 i, j 번째 인덱스에 주어진 값으로 대입
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				for (int j = 0; j < n; j++) {
					farm[i][j] = line.charAt(j) - '0';
				}
			}
			
			// 총합 저장할 변수 0으로 초기화
			int total = 0;
			
			// n은 항상 홀수이기 때문에 인덱스의 딱 중간 값이 되는 값을 저장해놓음 (이후 반복문에서 사용)
			int half = (n - 1) / 2;
			
			// 계산해야 할 열의 크기를 구할 때 사용할 변수
			int cols = 0;
			
			// n번의 행만큼 반복
			for (int i = 0; i < n; i++) {
				
				// half - cols 부터 half + cols 까지 반복 (중간을 기준으로 일정 값을 뺀만큼 반복하기 때문에 1, 3, 5 ... 만큼 반복)
				for (int j = half - cols; j <= half + cols; j++) {
					// 총합 갱신
					total += farm[i][j];
				}
				
				// 만약 i가 절반보다 작다면 cols를 1 증가시키고, 같거나 크다면 1 감소시킴
				if (i < half) {
					cols += 1;
				} else {
					cols -= 1;
				}
				
			}
			
			// 정답 문자열에 추가
			answer.append(String.format("#%d %d\n", t, total));
			
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}

}