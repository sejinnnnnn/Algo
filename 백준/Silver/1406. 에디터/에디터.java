import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 1406. 에디터
 * 처음에 LinkedList 하나를 사용해서 값을 중간에 추가해가는 방식으로 해결했지만 시간 초과
 * 왼쪽 문자열과 오른쪽 문자열을 따로 저장하는 Stack을 두 개 사용해서 해결
 *
 * 메모리 : 115,592 KB, 시간 : 724 ms
 */
public class Main {

    static Stack<Character> left;
    static Stack<Character> right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        left = new Stack<>();
        right = new Stack<>();

        char[] initValue = br.readLine().toCharArray();
        for (int i = 0; i < initValue.length; i++) {
            left.add(initValue[i]);
        }

        int cmdCount = Integer.parseInt(br.readLine());

        for (int c = 0; c < cmdCount; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken().charAt(0)) {
                case 'L':
                    if (left.size() == 0) continue;
                    right.push(left.pop());
                    break;
                case 'D':
                    if (right.size() == 0) continue;
                    left.push(right.pop());
                    break;
                case 'B':
                    if (left.size() == 0) continue;
                    left.pop();
                    break;
                case 'P':
                    char cInput = st.nextToken().charAt(0);
                    left.push(cInput);
                    break;
            }

        }

        if (left.size() != 0 && right.size() != 0) {
            char[] leftStr = new char[left.size()];
            char[] rightStr = new char[right.size()];

            for (int i = leftStr.length - 1; i >= 0; i--) {
                leftStr[i] = left.pop();
            }

            for (int i = 0; i < rightStr.length; i++) {
                rightStr[i] = right.pop();
            }

            String finalStr = String.valueOf(leftStr) + String.valueOf(rightStr);
            System.out.println(finalStr);
        } else if (left.size() != 0 && right.size() == 0) {
            char[] leftStr = new char[left.size()];
            for (int i = leftStr.length - 1; i >= 0; i--) {
                leftStr[i] = left.pop();
            }
            System.out.println(String.valueOf(leftStr));
        } else if (left.size() == 0 && right.size() != 0) {
            char[] rightStr = new char[right.size()];

            for (int i = 0; i < rightStr.length; i++) {
                rightStr[i] = right.pop();
            }
            System.out.println(String.valueOf(rightStr));
        } else System.out.println();




    }

}