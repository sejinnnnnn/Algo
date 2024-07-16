import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

//    static int[] heap = new int[100100];

    static HashMap<Integer, Integer> heap = new HashMap<>();
    static int idx = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int c = 0; c < n; c++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                if (idx == 0) answer.append(0).append('\n');
                else {
                    // 루트 노드 출력
                    answer.append(heap.get(1)).append('\n');

                    // 마지막 노드를 루트 노드로
                    heap.put(1, heap.get(idx));
                    heap.remove(idx--);

                    // 자식의 값이랑 비교하면서 자식이 더 작다면 값 바꾸기
                    int current = 1;
                    while (current <= idx) {
                        if (current * 2 > idx) break;

                        int currentVal = heap.get(current);
                        int leftChild = heap.get(current * 2);
                        int rightChild = heap.getOrDefault(current * 2 + 1, Integer.MAX_VALUE);

                        if (currentVal > leftChild && currentVal > rightChild) {
                            if (leftChild <= rightChild) {
                                heap.put(current, leftChild);
                                heap.put(current * 2, currentVal);
                                current *= 2;
                            } else {
                                heap.put(current, rightChild);
                                heap.put(current * 2 + 1, currentVal);
                                current = current * 2 + 1;
                            }
                        } else if (currentVal > leftChild) {
                            heap.put(current, leftChild);
                            heap.put(current * 2, currentVal);
                            current *= 2;
                        } else if (currentVal > rightChild) {
                            heap.put(current, rightChild);
                            heap.put(current * 2 + 1, currentVal);
                            current = current * 2 + 1;
                        } else break;

                    }

                }
            } else {
                heap.put(++idx, num);

                int current = idx;
                while (current > 1) {
                    int currentVal = heap.get(current);
                    int parent = heap.get(current / 2);
                    if (currentVal < parent) {
                        heap.put(current / 2, currentVal);
                        heap.put(current, parent);

                        current /= 2;
                    } else break;

                }

            }

//            System.out.println(heap + " " + idx);


        }

        System.out.println(answer.toString().trim());

    }

}