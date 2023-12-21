import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 4659. 비밀번호 발음하기
 * 1. 모음을 반드시 하나 이상 포함
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 돼
 * 3. 같은 글자 2번 연속으로 오면 안 돼 (ee, oo는 가능)
 */
public class Main {

    static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        nextPassword:
        while (true) {
            String inputStr = br.readLine();
            if (inputStr.equals("end")) break;

            char[] charArr = inputStr.toCharArray();

            int vowelCount = 0;

            boolean prevType = isVowel(charArr[0]); // true면 모음, false면 자음
            if (prevType) vowelCount++;
            int streak = 1;

            for (int i = 1; i < charArr.length; i++) {

//                System.out.println(prevType + " " + streak);

                if (isVowel(charArr[i])) {
                    vowelCount++;

                    if (prevType) streak++;
                    else {
                        prevType = true;
                        streak = 1;
                    }

                    if (charArr[i] != 'e' && charArr[i] != 'o' && charArr[i - 1] == charArr[i]) {
                        answer.append(String.format("<%s> is not acceptable.\n", inputStr));
                        continue nextPassword;
                    }

                } else {

                    if (!prevType) streak++;
                    else {
                        prevType = false;
                        streak = 1;
                    }

                    if (charArr[i - 1] == charArr[i]) {
                        answer.append(String.format("<%s> is not acceptable.\n", inputStr));
                        continue nextPassword;
                    }

                }

//                System.out.println(prevType + " " + streak);

                if (streak == 3) {
                    answer.append(String.format("<%s> is not acceptable.\n", inputStr));
                    continue nextPassword;
                }
            }

            if (vowelCount == 0) {
                answer.append(String.format("<%s> is not acceptable.\n", inputStr));
            } else {
                answer.append(String.format("<%s> is acceptable.\n", inputStr));
            }

        }

        System.out.println(answer.toString().trim());

    }

    private static boolean isVowel(char c) {
        for (int i = 0; i < 5; i++) {
            if (c == VOWELS[i]) return true;
        }

        return false;
    }

}