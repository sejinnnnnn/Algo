import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] cards = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		// Binary search를 하기 위해 정렬하기 (이진 탐색 선행조건)
		Arrays.sort(cards);
		
		int m = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < m; i++) {
			int current = Integer.parseInt(st.nextToken());
			
			int left = binaryLeft(cards, current);
			int right = binaryRight(cards, current);
			
			answer.append((right - left + 1)).append(" ");
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	private static int binaryRight(int[] arr, int target) {
		
		int left = 0;
		int right = arr.length - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] > target) right = mid - 1;
			else left = mid + 1;
		}
		
		return right;
	}
	
	private static int binaryLeft(int[] arr, int target) {
		
		int left = 0;
		int right = arr.length - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] < target) left = mid + 1;
			else right = mid - 1;
		}
		
		return left;
	}

}