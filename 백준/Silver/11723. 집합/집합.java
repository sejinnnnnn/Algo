import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 11723. 집합
 * 처음에는 Java 내장클래스인 TreeSet, HashSet을 활용하여 각 명령어에 맞는 메소드 호출하였으나, 시간이 구데기로 나옴 (2380, 1412)
 * 집합 요소의 범위를 체크해보니 1~20 이여서, 20 크기의 boolean 배열로 관리할 수 있겠다고 생각해서 바꿨음
 * 메모리 : 325,084 ms, 시간 : 992 ms
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

    /**
     * 집합에 현재 값이 있는지 없는지 확인하고, 정답 문자열에 0 또는 1 추가
     * @param x 확인할 값
     */
    private static void check(int x) {
        if (isIn[x]) answer.append("1\n");
        else answer.append("0\n");
    }

    /**
     * 주어진 값이 있으면 지우고, 없으면 추가하기
     * @param x toggle 할 값
     */
    private static void toggle(int x) {
        isIn[x] = !isIn[x];
    }

}