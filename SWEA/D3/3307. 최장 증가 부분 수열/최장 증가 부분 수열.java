import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D3 3307. 최장 증가 부분수열
 * 기존에 해결 방법을 O(n^2) 짜리만 알고 있었는데 오늘 수업시간에 배운 이진 탐색을 적용하여 보았다.
 * 동적 테이블 (길이 배열)을 활용하였고, "해당 인덱스 까지의 부분 수열 중 제일 작은 수"를 저장하여 활용하였다.
 * 
 * 메모리 : 21,008KB, 시간 : 126ms
 * @author 세진
 *
 */
public class Solution {
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 테케 수 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			// 수열 길이 n 입력받고 수열, 길이 배열 초기화
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			int[] lengthArr = new int[n];
			
			// 최댓값으로 길이배열 채우기 (최소값이 저장될 것이기 때문에 !)
			Arrays.fill(lengthArr, Integer.MAX_VALUE);
			
			// 공백으로 문자열 구분해서 수열 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 길이 배열 초기값 설정 (0번째 인덱스를 진행했다고 가정함)
			lengthArr[0] = arr[0];
			int lengthIdx = 1;
			
			// 1번쨰 인덱스부터, 길이 배열을 확인하면서 값을 갱신하기
			nextIdx:
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < lengthIdx; j++) {
					// 만약 길이 배열에 들어있는 값보다 현재 수열의 i번째 인덱스 값이 작다면 갱신하고 다음 요소 보기
					if (arr[i] < lengthArr[j]) {
						lengthArr[j] = arr[i];
						continue nextIdx;
					}
				}
				// 끝까지 왔다면, 길이배열의 뒤에 해당 값을 추가함
				lengthArr[lengthIdx++] = arr[i];

			}
			
//			System.out.println(Arrays.toString(lengthArr));
			
			// 정답 문자열 추가
			answer.append(String.format("#%d %d\n", t, lengthIdx));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}

}