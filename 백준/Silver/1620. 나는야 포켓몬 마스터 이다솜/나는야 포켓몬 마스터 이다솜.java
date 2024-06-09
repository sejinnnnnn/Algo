import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int idx = 1;
        HashMap<String, Integer> keyMap = new HashMap<>();
        HashMap<Integer, String> valueMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            keyMap.put(name, idx);
            valueMap.put(idx, name);
            idx++;
        }

        for (int i = 0; i < m; i++) {
            String query = br.readLine();
            char firstChar = query.toCharArray()[0];

            if (firstChar >= '0' && firstChar <= '9') {
                answer.append(valueMap.get(Integer.parseInt(query))).append("\n");
            } else {
                answer.append(keyMap.get(query)).append("\n");
            }
        }

        System.out.println(answer.toString().trim());

    }

}