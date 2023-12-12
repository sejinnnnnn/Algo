import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashSet<Integer> set;
    static StringBuilder answer;

    static final List<Integer> allList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();

        set = new HashSet<>();

        StringTokenizer st = null;

        int cmdCount = Integer.parseInt(br.readLine());

        for (int c = 0; c < cmdCount; c++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    set.add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    set.remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    check(Integer.parseInt(st.nextToken()));
                    break;
                case "toggle":
                    toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    set.clear();
                    set.addAll(allList);
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }

        System.out.println(answer.toString().trim());

    }

    private static void check(int x) {
        if (set.contains(x)) answer.append("1\n");
        else answer.append("0\n");
    }

    private static void toggle(int x) {
        if (set.contains(x)) set.remove(x);
        else set.add(x);
    }

}