import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static Stack<Character> left;
    static Stack<Character> right;
    static int leftIdx;
    static int rightIdx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        left = new Stack<>();
        right = new Stack<>();

        leftIdx = 0;
        rightIdx = 0;

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