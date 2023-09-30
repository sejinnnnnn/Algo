import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		int[] combArr = new int[n];
		
		for (int i = 0 ; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n / 2; i++) {
			combArr[n - i] = 1;
		}
		
		do {
			
			int startTotal = 0;
			int linkTotal = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (combArr[i] == combArr[j] && combArr[i] == 0) {
						startTotal += board[i][j];
					} else if (combArr[i] == combArr[j] && combArr[i] == 1) {
						linkTotal += board[i][j];
					}
				}
			}
			
			answer = Math.min(answer, Math.abs(startTotal - linkTotal));
			
			
		} while (npCombination(combArr, combArr.length));
		
		System.out.println(answer);
		
		
	}
	
	private static boolean npCombination(int[] arr, int size) {
		
		int i = size - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		if (i == 0) return false;
		
		int j = size - 1;
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		j = size - 1;
		while (i <= j) swap(arr, i++, j--);		
		
		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		
	}

}