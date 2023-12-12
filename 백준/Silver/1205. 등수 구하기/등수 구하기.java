import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1205. 등수 구하기
 * 처음엔 PriorityQueue 사용해서 풀다가 .. 예외처리가 많아지면서 코드가 복잡해지고 무엇보다 정답이 50% 대에서 자꾸 틀렸다
 * 그 후 값을 확인하면서 범위를 벗어나는지, 포함되는지를 체크하고 포함된다면 동률을 고려해서 순위를 출력했더니 정답 !
 *
 * 메모리 : 11,536 KB, 시간 : 84 ms
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int rCount = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        int largerCount = 0;
        int sameCount = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int current = Integer.parseInt(st.nextToken());
            if (current > newScore) largerCount++;
            else if (current == newScore) {
                largerCount++;
                sameCount++;
            }

//            System.out.println(largerCount + " " + sameCount);

            if (largerCount >= rCount) {
                System.out.println(-1);
                return;
            }
        }
//        System.out.println(sameCount);

        System.out.println((++largerCount - sameCount));


    }

}