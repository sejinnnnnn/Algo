import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());

            if (n == 1) {
                br.readLine();
                answer.append(1).append('\n');
                continue;
            }

            int[] priority = new int[n];
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            int priorityIdx = n - 1;
            int count = 0;

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                priority[i] = Integer.parseInt(st.nextToken());
                queue.offerLast(priority[i]);
            }

            Arrays.sort(priority); // 우선순위 정렬하기
//            System.out.println(Arrays.toString(priority));

            while (true) {
                int next = queue.pollFirst(); // 다음 꺼 우선순위 확인해 꺼내서

                // 만약에 다음 문서가 우선순위 제일 높은 것이라면
                if (next == priority[priorityIdx]) {
                    ++count; // 프린트 횟수 1 증가시켜

                    // 근데 이게 찾던 위치라면 현재 count가 정답
                    if (location == 0) break;

                    // 아니면 출력하고, 다음 우선순위랑 location 1칸 왼쪽으로 조정
                    --priorityIdx;
                    --location;


                } else { // 아니면 ?

                    // 다시 맨 뒤로 넣기
                    queue.offerLast(next);

                    // 위치 값을 조정하기 (1 빼주고, 0이라면 맨 뒤로 미루기)
                    if (location == 0) location = queue.size() - 1;
                    else --location;
                }

//                System.out.printf("count = %d, currentPriority = %d, next = %d, location = %d, queue = %s\n",
//                    count, priority[priorityIdx], next, location, queue);

            }

            answer.append(count).append('\n');

        }

        System.out.println(answer.toString().trim());

    }


}