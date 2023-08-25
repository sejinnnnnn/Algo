import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15961. 회전초밥
 * 처음에는 Queue를 활용하여 넣었다 뺐다 하려고 했는데 시간초과 날 것 같았다 (n이 3,000,000개임)
 * 그래서 내가 좋아하는 투 포인터를 사용하여 문제를 해결하였다
 * 메모리 : 168,564KB, 시간 : 536ms 
 * @author 세진
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// 입력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n, d, k 쿠폰 번호 입력
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken()) - 1;
		
		// 회전초밥 개수 만큼 배열 선언
		int[] sushi = new int[n];
		
		// 한 줄씩 입력받으면서 -1한 값을 배열에 넣어준다 (인덱스와 번호를 일치시키기 위해 -1 시킨다)
		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		// 초밥 종류 크기만큼의 배열 ate 선언
		int[] ate = new int[d];
		
		// 0 ~ k-1 까지 k개의 초밥으로 초기값 설정
		int left = 0;
		int right = k - 1;
		
		// 먹을 수 있는 초밥 종류 수 저장할 변수
		int count = 0;
		
		// 왼쪽부터 오른쪽까지 반복
		for (int i = left; i <= right; i++) {
			
			// 종류 배열에 현재 초밥 번호 1 증가시키기
			ate[sushi[i]]++;
			
			// 만약 현재 초밥 번호가 새로운 종류라면 (ate가 막 1이 되었다면) 초밥 종류 + 1
			if (ate[sushi[i]] == 1) count++;
			
		}
		
		// 최댓값 저장 변수
		int max = 0;
		
		// 만약 쿠폰 초밥을 안 먹었다면 초밥종류 + 1 한 값을 최댓값으로 초기화
		// 쿠폰초밥 종류를 먹었으면 그냥 현재 count값을 최댓값으로 초기화
		if (ate[coupon] == 0) max = count + 1;
		else max = count;
		
		// left 포인터가 n보다 작다면 반복
		while (left < n) {
			
			// 한 칸씩 이동
			left++;
			right++;
			
			// 만약 왼쪽으로 빠진 초밥의 종류가 1개밖에 없었다면 (1을 뺐을 때 0이 되었다면) count를 1 감소시킴
			if (--ate[sushi[left - 1]] == 0) count--;
			
			// right가 n보다 작다면 일반적으로 처리 : right번째 인덱스에 있는 초밥의 종류를 파악하고 만약 중복되지 않고 새로운 종류라면 count 1 증가시킴
			// right가 n보다 커졌다면 (원형이기 때문에 처리를 해줘야 함) : right % n (나머지) 값에 있는 인덱스를 참조
			if (right < n) {
				ate[sushi[right]]++;
				if (ate[sushi[right]] == 1) count++;
			} else {
				ate[sushi[right % n]]++;
				if (ate[sushi[right % n]] == 1) count++;
			}
			
			// 만약 쿠폰 초밥종류를 안 먹었다면 count + 1값을 현재까지의 최댓값과 비교해서 max를 갱신하고
			// 쿠폰 초밥 종류를 먹었으면 그냥 count값을 최댓값과 비교하여 갱신
			if (ate[coupon] == 0) max = Math.max(max, count + 1);
			else max = Math.max(max, count);
			
		}
		
		// 정답 출력
		System.out.println(max);
		
		
	}

}