import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int targetNumber;
    static int numCount;
    static boolean[] isBroken;

    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        targetNumber = Integer.parseInt(line);
        numCount = line.length();

        isBroken = new boolean[10];
        int bCount = Integer.parseInt(br.readLine());

        if (bCount != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < bCount; i++) {
                isBroken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        min = Math.abs(targetNumber - 100); // 현재 채널에서 +, - 만 눌러야 하는 횟수를 최소값으로 먼저 설정

        if (min <= numCount) { // 자릿수만큼 번호를 눌러야하는 횟수보다 min 값이 작거나 같다면 출력 후 종료
            System.out.println(min);
            return;
        }

        setChannel(0, 0);

        System.out.println(min);

    }

    private static void setChannel(int count, int click) {
        for (int i = 0; i < 10; i++) {
            if (!isBroken[i]) {
                int nextNum = click * 10 + i;
                int newDiff = String.valueOf(nextNum).length() + Math.abs(nextNum - targetNumber);
                min = Math.min(min, newDiff);

                if (count < 6) setChannel(count + 1, nextNum);
            }
        }

    }

}