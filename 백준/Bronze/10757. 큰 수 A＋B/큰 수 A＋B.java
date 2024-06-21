import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder answerStr = new StringBuilder();

        char[] A = st.nextToken().toCharArray();
        char[] B = st.nextToken().toCharArray();

        boolean isALong = A.length > B.length;

        int maxDigits = Math.max(A.length, B.length);
        int minDigits = Math.min(A.length, B.length);
        int diff = maxDigits - minDigits;

        int[] answer = new int[maxDigits];

        int ollim = 0;
        for (int i = 0; i < maxDigits; i++) {
            int result = ollim;
            if (isALong) {
                if (i < minDigits) {
                    result += A[maxDigits - 1 - i] - '0' + B[minDigits - 1 - i] - '0';
                } else {
                    result += A[maxDigits - 1 - i] - '0';
                }
            } else {
                if (i < minDigits) {
                    result += A[minDigits - 1 - i] - '0' + B[maxDigits - 1 - i] - '0';
                } else {
                    result += B[maxDigits - 1 - i] - '0';
                }
            }

            if (result >= 10) {
                ollim = result / 10;
            } else {
                ollim = 0;
            }

            answer[maxDigits - 1 - i] = result % 10;

        }

        if (ollim != 0) {
            answerStr.append(ollim);
        }

        for (int i = 0; i < answer.length; i++) {
            answerStr.append(answer[i]);
        }

        System.out.println(answerStr);

    }

}