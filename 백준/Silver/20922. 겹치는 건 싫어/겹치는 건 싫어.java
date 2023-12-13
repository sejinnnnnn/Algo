import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] appeared = new int[200001];
        int maxLength = -1;

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        appeared[arr[0]] = 1;
        maxLength = 1;

        while (right < n - 1) {
            right++;
            appeared[arr[right]]++;

            if (appeared[arr[right]] > k) {
                while (appeared[arr[right]] > k && left < right) {
                    appeared[arr[left++]]--;
                }
            }

            maxLength = Math.max(maxLength, right - left + 1);

//            System.out.println(left + " " + right + " " + appeared[5]);

        }

        System.out.println(maxLength);


    }

}