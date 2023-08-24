import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * D4 1251. 하나로
 * Prim 알고리즘을 이용하여 각 경로까지의 값을 구한 뒤, 현재 minEdge 배열보다 더 작은 값이 있다면 그 값으로 갱신해주고 Queue에 add
 * 설명을 잘 해주셔서 그런지 이해가 엄청 잘되었다 !
 * 메모리 : 33220KB, 시간 : 185ms
 * @author SSAFY
 *
 */
public class Solution {
	
	// 도착지와 비용을 저장할 Edge 클래스 정의
	static class Edge implements Comparable<Edge> {
		
		// 도착 정점 정보
		int vertex;
		
		// 비용
		double weight;
		
		// 생성자
		Edge(int vertex, double weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		// Double.compare를 이용하여 현재 객체의 weight와 비교할 객체의 weight값을 넣어줌
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		// 디버깅 시 사용했던 toString 메서드
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [vertex=");
			builder.append(vertex);
			builder.append(", weight=");
			builder.append(weight);
			builder.append("]");
			return builder.toString();
		}
		
		
		
	}

	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 공백문자열 구분 객체 생성
		StringTokenizer st = null;
		
		// 테케 입력받기
		int testCase = Integer.parseInt(br.readLine());
		
		// 테케만큼 반복
		for (int t = 1; t <= testCase; t++) {
			
			// 정점개수 입력받기
			int V = Integer.parseInt(br.readLine());
			
			// 좌표 저장할 pos 2차원 배열 초기화
			int[][] pos = new int[V][2];
			
			// 공백으로 끊고 x좌표들 0번째 idx에 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < V; i++) {
				pos[i][0] = Integer.parseInt(st.nextToken());
			}
			
			// 공백으로 끊고 y좌표들 1번째 idx에 다 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < V; i++) {
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 정답 구할 세율 입력받아 Double로 파싱
			double E = Double.parseDouble(br.readLine());
			
			// MST 비용의 총합
			double costTotal = 0;
			
			// 탐색 시 현재까지의 정점의 최소비용 minEdge 배열
			double[] minEdge = new double[V];
			
			// 무한대로 갱신
			Arrays.fill(minEdge, Double.MAX_VALUE);
			
			// 방문배열 선언
			boolean[] visited = new boolean[V];
			
			// 정점 탐색 숫자 저장할 변수
			int count = 0;
			
			// 임의 (0번째) 정점을 시작점으로 하기 (minEdge 배열에 자기자신까지의 거리 0 대입)
			minEdge[0] = 0;
			
			// 우선순위 큐
			PriorityQueue<Edge> queue = new PriorityQueue<>();
			
			// 0번째 인덱스, 비용 0인 Edge객체 만들어서 넣기
			queue.offer(new Edge(0, 0));
			
			// queue가 빌때까지
			while (!queue.isEmpty()) {
				
				// queue의 제일 앞의 값 꺼내오기
				Edge current = queue.poll();
				
				// 방문한 정점이라면 넘어가기
				if (visited[current.vertex]) continue;
				
				// 방문처리 후 전체 비용에 더해주기
				visited[current.vertex] = true;
				costTotal += current.weight;
				
				if (++count == V) break; // 모든 노드 탐색
				
				// V개만큼 보면서
				for (int i = 0; i < V; i++) {
					
					// 방문하지 않았다면
					if (!visited[i]) {
						
						// 현재 정점에서부터 해당 i번째 노드까지의 거리 계산하기 (땅굴을 무조건 팔 수 있기 때문에 거리를 구해본다)
						double distance = getDistance(pos[i][0], pos[i][1], pos[current.vertex][0], pos[current.vertex][1]);
						
						// 만약 거리가 현재 최소값 배열의 값보다 짧다면
						if (minEdge[i] > distance) {
							
							// 갱신 후 queue에 넣기
							minEdge[i] = distance;
							queue.offer(new Edge(i, distance));
						}
					}
				}
				
//				System.out.println(Arrays.toString(minEdge));
				
			}
			
			// 결과 저장할 변수
			double result = 0;
			
			// 결과의 각 터널의 제곱만큼 더하기
			for (int i = 0; i < V; i++) {
				result += Math.pow(minEdge[i], 2);
			}
			
			// 결과에 E만큼 곱하기 (문제 조건)
			result *= E;
			
			// 반올림하여 long타입 정수형으로 변환
			long resultRound = (long) Math.round(result);
			
			// 정답 문자열에 추가
			answer.append(String.format("#%d %d\n", t, resultRound));
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * 두 좌표 사이 거리를 구하는 메서드
	 * @param x1 첫번째 점의 x좌표
	 * @param y1 첫번째 점의 y좌표
	 * @param x2 두번째 점의 x좌표
	 * @param y2 두번째 점의 y좌표
	 * @return
	 */
	private static double getDistance(int x1, int y1, int x2, int y2) {
		double result = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		return result;
	}

}