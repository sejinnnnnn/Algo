import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D3 5607. [Professional] 조합
 * 문제의 핵심은 페르마 소정리를 이용하여 nCr 에서의 분모에 있는 값을 곱하기로 전환하는 문제이다
 * 일단 Divide and Conquer 방식으로 제곱을 구해야 함.. 굉장히 큰 제곱수이기 때문에
 * 근데 첨에 재귀호출로 함수 잘 짰는데 시간이 이상하게 안 줄었다 ㅠ
 * 
 * 메모리 : 87,684 KB, 시간 : 293 ms
 * @author 세진
 *
 */
public class Solution {
	
	// 문제에서 주어진 나머지 연산 값
	static final int MOD = 1234567891;
	
	// 팩토리얼 값을 memoization할 배열
	static long[] factorial;
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 테케 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// 공백으로 문자열 구분해서 n, r 에 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			// n + 1 크기로 배열 초기화 후 1번째 인덱스에 1! 값인 1 추가
			factorial = new long[n + 1];
			factorial[1] = 1;
			
			// n! % MOD 값을 구해서 저장하기
			for (int i = 2; i <= n; i++) {
				factorial[i] = (factorial[i - 1] * i) % MOD;
			}
			
			// 조합의 분모의 값인 r! * (n - r)! 값을 메모된 값에서 가져와 MOD하기
			long denominator = (factorial[r] * factorial[n - r]) % MOD;
			
			// n * 분모의 MOD-2제곱 값을 결과로 구해내기
			long result = (factorial[n] * square(denominator, MOD - 2)) % MOD;
			
			// 정답 문자열에 추가
			answer.append(String.format("#%d %d\n", t, result));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
	}
	
	/**
	 * 주어진 값과 횟수만큼 제곱하는 메소드 (Divide and Conquer 방식)
	 * @param value 제곱할 수
	 * @param count 제곱 횟수
	 * @return value의 count승
	 */
	private static long square(long value, int count) {
		
		if (count == 0) return 1;
		
		long result = square(value, count / 2);
		if (count % 2 == 0) return (result * result) % MOD;
		else return (result * result) % MOD * value % MOD;
		
	}

}