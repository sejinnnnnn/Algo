import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ 13977. 이항 계수와 쿼리
 * 페르마 소정리 연습을 하기 위해 비슷한 문제 풀어보기
 * 여러 개의 n에 대해 팩토리얼 테이블을 따로 구할 필요 없이 구해놓은 것을 사용해야 시간초과가 안난다 !
 * 
 * 메모리 : 208,776 KB, 시간 : 2,988 ms
 * @author 세진
 *
 */
public class Main {
	
	// 문제에서 주어진 나머지 연산 값
	static final int MOD = 1000000007;
	
	// 팩토리얼 테이블
	static long[] factorial = new long[4000001];
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 쿼리 개수 m 입력받기
		int m = Integer.parseInt(br.readLine());
		
		// n의 최댓값을 저장하는 변수 : 초기에 1로 초기화함
		int nMax = 1;
		
		// 0! = 1, 1! = 1 대입하기
		factorial[0] = 1;
		factorial[1] = 1;
		
		// m개 만큼 반복
		for (int t = 0; t < m; t++) {
			
			// 공백으로 문자열 구분해서 n, r에 저장하기
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			// 만약 n의 최댓값보다 지금 n이 더 크다면
			if (nMax < n) {
				
				// 최댓값 + 1 부터 n까지 반복하며 팩토리얼 테이블 업데이트
				for (int i = nMax + 1; i <= n; i++) {
					factorial[i] = factorial[i - 1] * i % MOD;
				}
				
				// 최댓값 저장하기
				nMax = n;
			}
			
			// 분모 값을 계산하기
			long denominator = (factorial[n - r] * factorial[r]) % MOD;
			
			// 결과 (n! * (r! * (n - r)!)^(MOD - 2)) : 페르마 소정리
			long result = (square(denominator, MOD - 2) % MOD * factorial[n]) % MOD;
			
			// 정답 추가
			answer.append(String.format("%d\n", result));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * value의 count만큼 제곱하는 메서드 : Divide and Conquer 적용
	 * @param value 제곱할 값
	 * @param count 제곱할 횟수
	 * @return value의 count제곱
	 */
	private static long square(long value, int count) {
		
		// 0이라면 1 반환
		if (count == 0) return 1;
		
		// 현재 count의 절반 값을 재귀호출해서 구해온 값에 MOD연산
		long result = square(value, count / 2) % MOD;
		
		// 만약 count가 짝수이면 result의 제곱의 MOD연산, 홀수이면 result 제곱 MOD연산 값에 value를 한번 더 곱해서 MOD연산
		if (count % 2 == 0) return (result * result) % MOD;
		else return (result * result) % MOD * value % MOD;
	}
	
	
}