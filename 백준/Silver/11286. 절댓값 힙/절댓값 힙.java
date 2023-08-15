import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 백준 11286. 절댓값 힙
 * 절댓값이 가장 작은 값을 출력하는 자료구조를 구현하는 문제
 * PriorityQueue 최소 힙을 이용
 * 절댓값이 제일 작은 요소가 와야하기 때문에 새로운 클래스를 정의해서 절댓값 , 부호를 따로 저장하였음
 * 클래스에 Comparable을 
 * 
 * @author 세진
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<IntWithSign> pq = new PriorityQueue<>();
		
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.size() == 0) answer.append("0\n");
				else {
					IntWithSign polled = pq.poll();
					answer.append(polled.value * polled.sign).append("\n");
				}
			} else {
				pq.add(new IntWithSign(num));
			}
		}
		
		System.out.println(answer.toString());
		
	}

}

class IntWithSign implements Comparable {
	
	int value;
	int sign;
	
	IntWithSign(int value) {
		if (value >= 0) {
			this.value = value;
			this.sign = 1;
		} else {
			this.value = value * (-1);
			this.sign = -1;
		}
	}

	@Override
	public int compareTo(Object o) {
		IntWithSign input = (IntWithSign) o;
		if (this.value != input.value) return this.value - input.value;
		return this.value * this.sign - input.value * input.sign;
	}
	
}