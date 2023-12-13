import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 20922. 겹치는 건 싫어
 * N 범위인 200000 까지의 배열을 만들어서 부분 수열에서의 해당 값이 몇 번 등장했는지 저장하기
 * 투 포인터를 활용해서 연속 부분 수열을 만들었다
 * 주어진 k보다 등장한 값이 커졌다면, k와 같아질 때 까지 left 포인터를 땡겨오기
 * 조건에 맞는 수열을 만들었다면 최댓값을 갱신하기
 *
 * 메모리 : 37,368 KB, 시간 : 304 ms
 */
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