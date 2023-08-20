import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    // 톱니바퀴 자석 정보
//    static int[][] magnets;
    static ArrayList<Integer>[] magnets;

    // 초기 맞닿는 부분 값 (처음에 2, 6임)
    static int[][] edges = { { -1, 2 }, { 6, 2 }, { 6, 2 }, { 6, -1 } };

    // 점수 계산되는 위치 값 (처음에 0임)
    static int[] points = { 0, 0, 0, 0 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase ; t++) {

            int k = Integer.parseInt(br.readLine());

//            magnets = new int[4][8];
            magnets = new ArrayList[4];

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                magnets[i] = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    magnets[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            for (int cmd = 0; cmd < k; cmd++) {
                st = new StringTokenizer(br.readLine());
                int magnetIdx = Integer.parseInt(st.nextToken()) - 1;
                int direction = Integer.parseInt(st.nextToken());

                int remainder = magnetIdx % 2;

                boolean[] haveToRotate = new boolean[4];
                haveToRotate[magnetIdx] = true;
//                rotateMagnet(magnetIdx, direction);

                for (int i = magnetIdx; i < 3; i++) {
                    if (!magnets[i].get(2).equals(magnets[i + 1].get(6))) haveToRotate[i + 1] = true;
                    else break;
                }

                for (int i = magnetIdx; i > 0; i--) {
                    if (!magnets[i].get(6).equals(magnets[i - 1].get(2))) haveToRotate[i - 1] = true;
                    else break;
                }

                for (int i = 0; i < 4; i++) {
                    if (haveToRotate[i]) {
                        if (i % 2 == remainder) rotateMagnet(i, direction);
                        else rotateMagnet(i, direction * (-1));
                    }
                }

            }

            int total = 0;
            for (int i = 0; i < 4; i++) {
//                System.out.print(magnets[i].get(0) + " ");
                total += magnets[i].get(0) << i;
            }
//            System.out.println();

            answer.append(String.format("#%d %d\n", t, total));

        }

        System.out.println(answer.toString().trim());

    }

    private static void rotateMagnet(int magnet, int direction) {

        if (direction == 1) {
            int top = magnets[magnet].remove(7);
            magnets[magnet].add(0, top);
        } else if (direction == -1) {
            int top = magnets[magnet].remove(0);
            magnets[magnet].add(top);
        }

//        System.out.print((magnet + 1) + " : ");
//        for (int i = 0; i < 8; i++) {
//            System.out.print(magnets[magnet].get(i) + " ");
//        }
//        System.out.println();

    }

}