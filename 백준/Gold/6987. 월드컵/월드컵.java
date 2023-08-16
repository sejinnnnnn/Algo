import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static final int GROUP_SIZE = 4;
	static final int TEAM_SIZE = 6;
	static int[][] scores;
	static int result = 0;
	
	static ArrayList<int[]> matches;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		matches = new ArrayList<>();
		makeMatches(0, 0, new int[2]);
		
		nextCase:
		for (int g = 0; g < GROUP_SIZE; g++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			scores = new int[TEAM_SIZE][3];
			result = 0;
			
			for (int i = 0; i < TEAM_SIZE; i++) {
				
				for (int j = 0; j < 3; j++) {
					scores[i][j] = Integer.parseInt(st.nextToken());
					if (scores[i][j] == 6) {
						answer.append("0 ");
						continue nextCase;
					}
				}
				
			}
			
			playMatches(0, new int[TEAM_SIZE][3]);
			
			answer.append(String.format("%d ", result));
			
		}

		System.out.println(answer.toString());
		
	}
	
	
	private static void makeMatches(int count, int start, int[] selected) {
		
		if (count == 2) {
			matches.add(Arrays.copyOf(selected, 2));
			return;
		}
		
		for (int i = start; i < TEAM_SIZE; i++) {
			selected[count] = i;
			makeMatches(count + 1, i + 1, selected);
		}
		
	}
	
	
	private static void playMatches(int matchCount, int[][] boards) {
		
		// 기저조건
		if (matchCount == matches.size()) {
			result = 1;
			return;
		}
		
		// 완전탐색
		int[] current = matches.get(matchCount);
		
		int home = current[0];
		int away = current[1];
		
		// case 1 : 홈 Win
		
		// 일단 스코어랑 현재 보드판이랑 비교 후 홈팀이 이길 수 있는 경우가 있다면 다음 탐색 진행
		if (boards[home][0] + 1 <= scores[home][0] && boards[away][2] + 1 <= scores[away][2]) {
			boards[home][0]++;
			boards[away][2]++;
//			System.out.printf("Match %d : Home (%d) win / Away (%d) lose\n", matchCount, home, away);
//			System.out.printf("Home : %dwin %ddraw %dloss\n", boards[home][0], boards[home][1], boards[home][2]);
//			System.out.printf("Away : %dwin %ddraw %dloss\n", boards[away][0], boards[away][1], boards[away][2]);
//			printScores(boards);
			playMatches(matchCount + 1, boards);
			boards[home][0]--;
			boards[away][2]--;
		}
		
		// case 2 : 무승부
		// 일단 스코어랑 현재 보드판이랑 비교 후 비길 수 있는 경우가 있다면 다음 탐색 진행
		if (boards[home][1] + 1 <= scores[home][1] && boards[away][1] + 1 <= scores[away][1]) {
			boards[home][1]++;
			boards[away][1]++;
//			System.out.printf("Match %d : Home (%d) draw / Away (%d) draw\n", matchCount, home, away);
//			System.out.printf("Home : %dwin %ddraw %dloss\n", boards[home][0], boards[home][1], boards[home][2]);
//			System.out.printf("Away : %dwin %ddraw %dloss\n", boards[away][0], boards[away][1], boards[away][2]);
//			printScores(boards);
			playMatches(matchCount + 1, boards);
			boards[home][1]--;
			boards[away][1]--;
		}

		
		// case 3 : 어웨이 Win
		// 일단 스코어랑 현재 보드판이랑 비교 후 어웨이팀이 이길 수 있는 경우가 있다면 다음 탐색 진행
		if (boards[home][2] + 1 <= scores[home][2] && boards[away][0] + 1 <= scores[away][0]) {
			boards[home][2]++;
			boards[away][0]++;
//			System.out.printf("Match %d : Home (%d) lose / Away (%d) win\n", matchCount, home, away);
//			System.out.printf("Home : %dwin %ddraw %dloss\n", boards[home][0], boards[home][1], boards[home][2]);
//			System.out.printf("Away : %dwin %ddraw %dloss\n", boards[away][0], boards[away][1], boards[away][2]);
//			printScores(boards);
			playMatches(matchCount + 1, boards);
			boards[home][2]--;
			boards[away][0]--;
		}
		
	}
	
	private static void printScores(int[][] boards) {
		
		System.out.printf("Answers\tBoards\n");
		for (int i = 0; i < TEAM_SIZE; i++) {
			System.out.println(Arrays.toString(scores[i]) + " " + Arrays.toString(boards[i]));
		}
		
	}

}