import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        TreeSet<Integer> set = new TreeSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (!set.contains(arr[i])) set.add(arr[i]);
        }

        HashMap<Integer, Integer> sequenceMap = new HashMap<>();
        Iterator<Integer> it = set.iterator();

        for (int i = 0; i < set.size(); i++) {
            sequenceMap.put(it.next(), i);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append(sequenceMap.get(arr[i])).append(' ');
        }

        System.out.println(answer.toString().trim());
    }

}