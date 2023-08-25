import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken()) - 1;
		
		int[] sushi = new int[n];
		
		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		int[] ate = new int[d];
		
		int left = 0;
		int right = k - 1;
		
		int count = 0;
		
		for (int i = left; i <= right; i++) {
			
			ate[sushi[i]]++;
			if (ate[sushi[i]] == 1) count++;
			
		}
		
		int max = 0;
		
		if (ate[coupon] == 0) max = count + 1;
		else max = count;
		
		while (left < n) {
			
			left++;
			right++;
			
			if (--ate[sushi[left - 1]] == 0) count--;
			
			if (right < n) {
				ate[sushi[right]]++;
				if (ate[sushi[right]] == 1) count++;
			} else {
				ate[sushi[right % n]]++;
				if (ate[sushi[right % n]] == 1) count++;
			}
			
//			for (int i = 0; i < ate.length; i++) {
//				if (ate[i] != 0) System.out.print(i + " : " + ate[i] + ", ");
//			}
			
//			System.out.println(Arrays.toString(ate));
			
			if (ate[coupon] == 0) max = Math.max(max, count + 1);
			else max = Math.max(max, count);
			
//			System.out.println("count : " + count + " / max : " + max);
			
			
		}
		
		
		System.out.println(max);
		
		
	}

}