import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * SW 5658. 보물상자 비밀번호
 * 배열을 실수 없이 잘 돌리기
 * TreeSet을 이용하여 중복 요소를 추가하지 않게 하였다 ~!
 * 
 * 메모리 : 19,900KB, 시간 : 113 ms
 * @author 세진
 *
 */
public class Solution {
	
	// n, n/4, (4 * nd4) 크기의 square 2차원 배열, k 저장 변수
	static int n;
	static int nd4;
	static int[][] square;
	static int k;
	
	// 만들어진 숫자 저장할 숫자 집합 numSet
	static TreeSet<Integer> numSet;
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			// n, k 입력받아 nd4에 n/4 저장하고 배열 초기화, numSet 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			nd4 = n / 4;
			square = new int[4][nd4];
			
			numSet = new TreeSet<>();
			
			// 입력 문자열을 char 배열로 변환
			char[] line = br.readLine().toCharArray();
			
			// 4 * nd4 만큼 반복하면서 한 문자씩 보면서 정수로 변환 (0~15)
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < nd4; j++) {
					if (line[i * nd4 + j] >= 0 && line[i * nd4 + j] <= '9') square[i][j] = line[i * nd4 + j] - '0';
					else square[i][j] = Integer.parseInt(line[i * nd4 + j] + "", 16);
				}
			}
			
			// nd4만큼 반복하며 숫자 만들어서 집합에 추가하고, 돌리는 메서드 수행
			for (int i = 0; i < nd4; i++) {
				addList();
				rotate();
			}
			
			// 내림차순 Iterator를 반환하는 메서드를 호출하여 Iterator 저장하기
			Iterator<Integer> it = numSet.descendingIterator();
			
			// k - 1번 반복하여 iterator를 k번째가 딱 나올 수 있게 전진시키기
			for (int i = 0; i < k - 1; i++) {
//				System.out.println(it.next());
				it.next();
			}
			
			// 테케와 it를 한칸 더 전진시킨 값을 정답 문자열에 추가하기
			answer.append(String.format("#%d %d\n", t, it.next()));
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * 배열 회전 메서드
	 */
	private static void rotate() {
		
		// 0,0 값을 첫 번째에 저장해놓기
		int temp = square[0][0];
		
		// 4 * nd4번 반복하며, 내 앞의 인덱스 값을 내 값으로 끌어댕겨오기
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < nd4 - 1; j++) {
				square[i][j] = square[i][j + 1];
			}
			// 마지막 열 값은 다음 행의 첫번째 값을 대입해주기
			square[i][nd4 - 1] = square[(i + 1) % 4][0];
		}
		
		// 마지막 행, 마지막 열 값은 미리 저장해놓은 temp로 대입하기
		square[3][nd4 - 1] = temp;
	}
	
	/**
	 * 16진수 숫자를 만들어 리스트에 추가하기
	 */
	private static void addList() {
	
		// 4 * nd4번 반복하면서 한 행에 해당하는 숫자를 만들기 (16의 제곱씩 곱하기)
		for (int i = 0; i < 4; i++) {
			int num = 0;
			for (int j = 0; j < nd4; j++) {
				num += square[i][j] * Math.pow(16, nd4 - 1 - j);
			}
			
			// numSet 집합에 추가 (중복되면 알아서 추가안됨)
			numSet.add(num);
		}
	
	}

}