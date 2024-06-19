import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 입력 객체인 BufferedReader 선언 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 출력 문자열 객체인 StringBuilder 선언 및 초기화
		StringBuilder answer = new StringBuilder();
		
		// 맨 첫 줄에 등장하는 k 입력받아 int로 parsing후 저장
		int foldCount = Integer.parseInt(br.readLine());
		
		// 접는 방향 저장할 배열선언
		char[] folds = new char[2 * foldCount];
		
		// 공백으로 문자열 구분
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 접는개수만큼 접는방향 저장
		for (int i = 0; i < 2 * foldCount; i++) {
			folds[i] = st.nextToken().charAt(0);
		}
		
		char[] reverseFolds = new char[folds.length];
		
		for (int i = 0; i < folds.length; i++) {
			reverseFolds[i] = folds[folds.length - 1 - i];
		}
		
		
		
		// 어디 구멍뚫는지 저장할 숫자 저장
		int where = Integer.parseInt(br.readLine());
		
		// 현재 종이 저장할 2차원 배열 선언 및 초기화
		int[][] current = new int[][] { { where } };
		
		// 접는 순서만큼 반복
		for (int i = 0; i < folds.length; i++) {
			
			// D라면
			if (reverseFolds[i] == 'D') {
				
				int[][] next = new int[current.length * 2][current[0].length];
				
				for (int j = current.length; j < next.length; j++) {
					for (int k = 0; k < next[0].length; k++) {
						next[j][k] = current[j - current.length][k];
						
						if (next[j][k] - 2 < 0) next[next.length - 1 - j][k] = next[j][k] + 2;
						else next[next.length - 1 - j][k] = next[j][k] - 2;
						
					}
				}
				
				current = next;
				
				
			} else if (reverseFolds[i] == 'U') { // U라면
				
				int[][] next = new int[current.length * 2][current[0].length];
				
				for (int j = 0; j < current.length; j++) {
					for (int k = 0; k < next[0].length; k++) {
						next[j][k] = current[j][k];
						
						next[next.length - 1 - j][k] = (next[j][k] + 2) % 4;
						
					}
				}
				
				current = next;
				
			} else if (reverseFolds[i] == 'R') { // R이라면
				
				int[][] next = new int[current.length][current[0].length * 2];
				
				for (int j = 0; j < current.length; j++) {
					for (int k = current[0].length; k < next[0].length; k++) {
						next[j][k] = current[j][k - current[0].length];
						
						if (next[j][k] == 0) next[j][next[0].length - 1 - k] = 1;
						else if (next[j][k] == 1) next[j][next[0].length - 1 - k] = 0;
						else if (next[j][k] == 2) next[j][next[0].length - 1 - k] = 3;
						else if (next[j][k] == 3) next[j][next[0].length - 1 - k] = 2;
					}
				}
				
				current = next;
				
			} else if (reverseFolds[i] == 'L') { // L이라면
				
				int[][] next = new int[current.length][current[0].length * 2];
				
				for (int j = 0; j < current.length; j++) {
					for (int k = 0; k < current[0].length; k++) {
						next[j][k] = current[j][k];
						
						if (next[j][k] == 0) next[j][current[0].length - 1 + k] = 1;
						else if (next[j][k] == 1) next[j][current[0].length - 1 + k] = 0;
						else if (next[j][k] == 2) next[j][current[0].length - 1 + k] = 3;
						else if (next[j][k] == 3) next[j][current[0].length - 1 + k] = 2;
					}
				}
				
				current = next;
				
			}
			
//			for (int j = 0; j < current.length; j++) {
//				System.out.println(Arrays.toString(current[j]));
//			}
//			
//			System.out.println();
			
		}
		
		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current[0].length; j++) {
				answer.append(String.format("%d ", current[i][j]));
			}
			answer.append("\n");
		}
		
		System.out.println(answer.toString().trim());
		
	}

}