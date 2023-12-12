import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 11723. 집합
 * Java 내장클래스인 HashSet을 활용하여 각 명령어에 맞는 메소드 호출하였음
 *
 * 메모리 : 325,348 ms, 시간 : 1,412 ms
 */
public class Main {

    static StringBuilder answer;

    static boolean[] isIn = new boolean[21];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();

        StringTokenizer st = null;

        int cmdCount = Integer.parseInt(br.readLine());

        for (int c = 0; c < cmdCount; c++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    isIn[Integer.parseInt(st.nextToken())] = true;
                    break;
                case "remove":
                    isIn[Integer.parseInt(st.nextToken())] = false;
                    break;
                case "check":
                    check(Integer.parseInt(st.nextToken()));
                    break;
                case "toggle":
                    toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    Arrays.fill(isIn, true);
                    break;
                case "empty":
                    Arrays.fill(isIn, false);
                    break;
            }
        }

        System.out.println(answer.toString().trim());

    }

    private static void check(int x) {
        if (isIn[x]) answer.append("1\n");
        else answer.append("0\n");
    }

    private static void toggle(int x) {
        if (isIn[x]) isIn[x] = false;
        else isIn[x] = true;
    }

}