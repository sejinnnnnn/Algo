import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int k;
	static int[][] originalArr;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		originalArr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				originalArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] r = new int[k];
		int[] c = new int[k];
		int[] s = new int[k];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken()) - 1;
			c[i] = Integer.parseInt(st.nextToken()) - 1;
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] permArr = new int[k];
		
		for (int i = 0; i < k; i++) {
			permArr[i] = i;
		}
		
		int min = Integer.MAX_VALUE;
		
		do {
			
			int[][] operatedArr = new int[n][];
			
			for (int i = 0; i < n; i++) {
				operatedArr[i] = Arrays.copyOf(originalArr[i], m);
			}
			
			for (int p = 0; p < k; p++) {
				
//				System.out.println(r[permArr[p]] + " " + s[permArr[p]] + " " + c[permArr[p]]);
				
				for (int depth = 0; depth < s[permArr[p]]; depth++) {
					
					int xStart = r[permArr[p]] - s[permArr[p]] + depth;
					int yStart = c[permArr[p]] - s[permArr[p]] + depth;
					
					int temp = operatedArr[xStart][yStart];
					
					for (int i = xStart; i < r[permArr[p]] + s[permArr[p]] - depth; i++) {
						operatedArr[i][yStart] = operatedArr[i + 1][yStart];
					}
					
					xStart = r[permArr[p]] + s[permArr[p]] - depth;
					
					for (int i = yStart; i < c[permArr[p]] + s[permArr[p]] - depth; i++) {
//						System.out.println(i);
						operatedArr[xStart][i] = operatedArr[xStart][i + 1];
					}
					
					yStart = c[permArr[p]] + s[permArr[p]] - depth;
					
					for (int i = xStart; i > r[permArr[p]] - s[permArr[p]] + depth; i--) {
						operatedArr[i][yStart] = operatedArr[i - 1][yStart];
					}
					
					xStart = r[permArr[p]] - s[permArr[p]] + depth;
					
					for (int i = yStart; i > c[permArr[p]] - s[permArr[p]] + depth; i--) {
						operatedArr[xStart][i] = operatedArr[xStart][i - 1];
					}
					
					operatedArr[r[permArr[p]] - s[permArr[p]] + depth][c[permArr[p]] - s[permArr[p]] + depth + 1] = temp;
					
				}
				
			}
			
			for (int i = 0; i < n; i++) {
				int rowTotal = 0;
				for (int j = 0; j < m; j++) {
					rowTotal += operatedArr[i][j];
				}
				if (rowTotal < min) min = rowTotal;
			}
			
			
		} while (nextPermutation(permArr));
		
		System.out.println(min);
		
	}
	
	private static boolean nextPermutation(int[] list) {
		
		int i = list.length - 1;
		int j = list.length - 1;
		
		// 꼭대기 찾기
		while (i > 0 && list[i - 1] >= list[i]) i--;
		
		// i가 0까지 갔다면 다음으로 만들 순열이 없음 (false 반환)
		if (i == 0) return false;
		
		// 꼭대기 직전 (i - 1) 위치에 교환할 한 단계 큰 수 뒤쪽부터 찾기
		while (list[i - 1] >= list[j]) j--;
		
		// 꼭대기 직전 (i - 1) 위치의 수와 한 단계 큰 수를 교환
		swap(list, i - 1, j);
		
		// 꼭대기 자리부터 맨 뒤까지의 수를 오름차순 형태로 바꾼다
		j = list.length - 1;
		while (i < j) {
			swap(list, i++, j--);
		}
		
		
		return true;
	}
	
	private static void swap(int[] list, int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
	
}