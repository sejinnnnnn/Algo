import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int L;
	static int C;
	
	static char[] letters;
	
	static char[] password;
	static StringBuilder answer;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		letters = new char[C];
//		char[] lettersComb = new char[C];
		
		password = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			letters[i] = st.nextToken().charAt(0);
		}
		
//		int count = 0;
//		while (count++ < L) lettersComb[count] = 1;
		
		Arrays.sort(letters);
		
		makeCombination(0, 0, 0, 0);
		
		System.out.println(answer.toString().trim());
		
//		do {
//			int cCount = 0;
//			int vCount = 0;
//			for (int i = 0; i < C; i++) {
//				if (lettersComb[i] == 0) continue;
//				
//				if (isVowel(letters[i])) cCount++;
//				else vCount++;
//			}
//			
//			if (cCount < 2 || vCount < 1) continue;
//			
//			char[] pwLetters = new char[L];
//			int pwIdx = 0;
//			
//			System.out.print("Letters : ");
//			
//			for (int i = 0; i < C; i++) {
//				if (lettersComb[i] == 0) continue;
//				pwLetters[pwIdx++] = letters[i];
//				System.out.print((char)letters[i]);
//			}
//			System.out.println();
//			
//			do {
//				
//				for (int i = 0; i < L; i++) {
//					answer.append((char)pwLetters[i]); 
//				}
//				answer.append("\n");
//				
//			} while (nextPermutation(pwLetters, L));
//			
//			
//		} while (nextPermutation(lettersComb, C));
		
//		System.out.println(answer.toString().trim());
		
	}
	
	private static void makeCombination(int count, int start, int cCount, int vCount) {
		
		if (count == L) {
			if (cCount >= 2 && vCount >= 1) {
				answer.append(String.copyValueOf(password));
				answer.append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) { // i : 뽑는 수
			
			password[count] = letters[i];
			if (isVowel(letters[i])) makeCombination(count + 1, i + 1, cCount, vCount + 1);
			else makeCombination(count + 1, i + 1, cCount + 1, vCount);
		}
		
		
	}
	
	
	private static void makePermutation(int count, int cCount, int vCount, boolean[] isSelected) { // count + 1 번째 주사위 한 번 던지기, count : 기존까지 던져진 주사위 횟수
		
		if (count == L) {
			if (cCount >= 2 && vCount >= 1) {
				answer.append(String.copyValueOf(password));
				answer.append("\n");
			}
			return;
		}
		
		for (int i = 0; i < C; i++) {
			
			if (isSelected[i]) continue;
			
			password[count] = letters[i];
			isSelected[i] = true;
			
			if (isVowel(letters[i])) makePermutation(count + 1, cCount, vCount + 1, isSelected);
			else makePermutation(count + 1, cCount + 1, vCount, isSelected);
			
			isSelected[i] = false;
		}
		
	}
	
	
	private static boolean nextPermutation(char[] arr, int size) {
		
		int i = size - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if (i == 0) return false;
		
		int j = size - 1;
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		j = size - 1;
		while (i < j) swap(arr, i++, j--);
		
		return true;
		
	}
	
	private static void swap(char[] arr, int a, int b) {
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private static boolean isVowel(char a) {
		return a == 'a' || a == 'e' || a == 'i' || a == 'o' || a =='u';
	}

}