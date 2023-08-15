import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int s, p;
	static char[] dna;
	static int[] count;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		dna = br.readLine().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		int[] restrict = new int[4];
		
		for (int i = 0; i < 4; i++) {
			restrict[i] = Integer.parseInt(st.nextToken());
		}
		
		int point = 0;
		int answer = 0;
		count = new int[4];
		
		for (int i = 0; i < p; i++) {
			switch (dna[i]) {
			case 'A':
				count[0]++;
				break;
			case 'C':
				count[1]++;
				break;
			case 'G':
				count[2]++;
				break;
			case 'T':
				count[3]++;
				break;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if (count[i] < restrict[i]) break;
			if (i == 3) answer++;
		}
		
		while (point + p < s) {
//			System.out.println(point + " " + (point + p));
			point++;
			checkDNA(point);
			
			for (int i = 0; i < 4; i++) {
				if (count[i] < restrict[i]) break;
				if (i == 3) answer++;
			}
			
			
		}
		
		System.out.println(answer);
		
	}
	
	public static void checkDNA(int point) {

		switch (dna[point - 1]) {
		case 'A':
			count[0]--;
			break;
		case 'C':
			count[1]--;
			break;
		case 'G':
			count[2]--;
			break;
		case 'T':
			count[3]--;
			break;
		}

		switch (dna[point + p - 1]) {
		case 'A':
			count[0]++;
			break;
		case 'C':
			count[1]++;
			break;
		case 'G':
			count[2]++;
			break;
		case 'T':
			count[3]++;
			break;
		}
		
		
		
	}
	

}