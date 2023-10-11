import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			map = new int[101][101];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 시작점을 (0, 0), 도착점을 1사분면 양수로 만들기 위해 두 값을 빼준 뒤 절댓값을 씌워 저장하기
			int x = Math.abs(x2 - x1);
			int y = Math.abs(y2 - y1);
			
			// y = x 그래프 위에서 가장 가까운 점 좌표 찾기
			int point = (x + y) / 2;
			
			// (x, y) 부터 (point, point) 까지의 거리 구하기
			int additional = Math.abs(x - point) + Math.abs(y - point);
			
			// 정답 추가
			answer.append(String.format("#%d %d\n", t, 2 * point + additional));
		}
		
		System.out.println(answer.toString().trim());
		
	}

}