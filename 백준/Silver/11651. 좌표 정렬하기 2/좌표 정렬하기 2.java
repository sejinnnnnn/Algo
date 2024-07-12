import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 백준 11651. 좌표 정렬하기 2 (실버 5)
 *
 * 퀵 소트 직접 구현해서 풀어보기
 * y 좌표 증가하는 순으로, 같으면 x좌표가 증가하는 순으로 정렬
 */
public class Main {

    static int n;
    static int[][] posArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        posArr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            posArr[i][0] = Integer.parseInt(st.nextToken());
            posArr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(posArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });

//        quickSort(posArr, 0, n - 1);

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(posArr[i]));
//        }

        for (int i = 0; i < n; i++) {
            answer.append(String.format("%d %d\n", posArr[i][0], posArr[i][1]));
        }

        System.out.println(answer.toString().trim());
    }

//    private static void quickSort(int[][] arr, int start, int end) {
//        // 시작과 끝 값이 같거나 크다면, 정렬할 요소가 1개 혹은 0개이기 때문에 메소드 종료 (기저조건)
//        if (start >= end) return;
//
//        // 피벗을 첫 시작 값으로 잡기
//        int pivot = (start + end) / 2;
//
//        swap(arr, pivot, end);
//
//        // 왼쪽과 오른쪽 좌표 설정하기
//        int left = start;
//        int right = end - 1;
//
//        // 왼쪽보다 오른쪽이 작다면 계속 반복
//        while (left <= right) {
//            // pivot보다 큰 값이 나올 때 까지 left 증가
//            while (left <= end && (arr[pivot][1] > arr[left][1] || (arr[pivot][1] == arr[left][1] && arr[pivot][0] > arr[left][0]))) {
//                ++left;
//            }
//
//            // pivot보다 작은 값이 나올 때 까지 right 감소
//            while (right > start && (arr[pivot][1] < arr[right][1] || (arr[pivot][1] == arr[right][1] && arr[pivot][0] < arr[right][0]))) {
//                --right;
//            }
//
//            if (left < right) {
//                swap(arr, left, right);
//            }
//        }
//
//        swap(arr, left, end);
//
//        quickSort(arr, start, right - 1);
//        quickSort(arr, right + 1, end);
//    }
//
//    private static void swap(int[][] arr, int a, int b) {
//        int[] temp = arr[a];
//        arr[a] = arr[b];
//        arr[b] = temp;
//    }

}