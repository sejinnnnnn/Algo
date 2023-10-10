import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		char[] charList = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
		
		int answer = 0;
		
		for (int i = 0; i < charList.length; i++) {
			int dialToNum = charList[i] - 'A';
			switch (dialToNum) {
			case 0:
			case 1:
			case 2:
				answer += 3;
				break;
			case 3:
			case 4:
			case 5:
				answer += 4;
				break;
			case 6:
			case 7:
			case 8:
				answer += 5;
				break;
			case 9:
			case 10:
			case 11:
				answer += 6;
				break;
			case 12:
			case 13:
			case 14:
				answer += 7;
				break;
			case 15:
			case 16:
			case 17:
			case 18:
				answer += 8;
				break;
			case 19:
			case 20:
			case 21:
				answer += 9;
				break;
			case 22:
			case 23:
			case 24:
			case 25:
				answer += 10;
				break;
			}
		}
		
		System.out.println(answer);
		
	}

}