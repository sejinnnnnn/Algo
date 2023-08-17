import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] company = new int[2];
			int[] home = new int[2];
			
			int[][] clients = new int[n][2];
			int[] np = new int[n];
			
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < n; i++) {
				clients[i][0] = Integer.parseInt(st.nextToken());
				clients[i][1] = Integer.parseInt(st.nextToken());
				
				np[i] = i;
			}
			
			int min = Integer.MAX_VALUE;
			
			int total = 0;
			
			total += Math.abs(company[0] - clients[np[0]][0]) + Math.abs(company[1] - clients[np[0]][1]);
			
			for (int i = 1; i < n; i++) {
				total += Math.abs(clients[np[i - 1]][0] - clients[np[i]][0]) + Math.abs(clients[np[i - 1]][1] - clients[np[i]][1]);
			}
			
			total += Math.abs(clients[np[n - 1]][0] - home[0]) + Math.abs(clients[np[n - 1]][1] - home[1]);
			
			if (total < min) {
				min = total;
			}
			
			nextTotal:
			while (nextPermutation(n, np)) {
				
				total = 0;
				
				total += Math.abs(company[0] - clients[np[0]][0]) + Math.abs(company[1] - clients[np[0]][1]);
				if (total > min) continue nextTotal;
				
				for (int i = 1; i < n; i++) {
					total += Math.abs(clients[np[i - 1]][0] - clients[np[i]][0]) + Math.abs(clients[np[i - 1]][1] - clients[np[i]][1]);
					if (total > min) continue nextTotal;
				}
				
				total += Math.abs(clients[np[n - 1]][0] - home[0]) + Math.abs(clients[np[n - 1]][1] - home[1]);
				
				if (total < min) {
					min = total;
				}
				
			}
			
			
			answer.append(String.format("#%d %d\n", t, min));
			
		}
		
		System.out.println(answer.toString().trim());
		
	}
	
	
	private static boolean nextPermutation(int length, int[] arr) {
		
		int i = length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if (i == 0) return false;
		
		int j = length - 1;
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		j = length - 1;
		while (i < j) {
			swap(arr, i++, j--);
		}
		
		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		
	}
	
}