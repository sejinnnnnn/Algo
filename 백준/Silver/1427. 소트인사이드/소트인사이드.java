import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int[] arr = new int[(int) Math.log10(n) + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = n % 10;
            n /= 10;
        }

        int[] sorted = reverseMergeSort(arr, arr.length);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < sorted.length; i++) {
            answer.append(sorted[i]);
        }
        System.out.println(answer);
    }

    private static int[] mergeSort(int[] arr, int size) {
        if (size == 1) {
            return new int[] { arr[0] };
        }

        if (size == 2) {
            if (arr[0] < arr[1]) return new int[] { arr[0], arr[1] };
            else return new int[] { arr[1], arr[0] };
        }

        int half = size / 2;
        int[] part1;
        int[] part2;

        int[] result1;
        int[] result2;

        if ((size & 1) == 1) {
            part1 = new int[half];
            part2 = new int[half + 1];

            for (int i = 0; i < part1.length; i++) {
                part1[i] = arr[i];
            }

            for (int i = 0; i < part2.length; i++) {
                part2[i] = arr[half + i];
            }

            result1 = mergeSort(part1, half);
            result2 = mergeSort(part2, half + 1);

        } else {
            part1 = new int[half];
            part2 = new int[half];

            for (int i = 0; i < part1.length; i++) {
                part1[i] = arr[i];
                part2[i] = arr[half + i];
            }

            result1 = mergeSort(part1, half);
            result2 = mergeSort(part2, half);
        }

        int[] result = new int[arr.length];

        int r1Idx = 0;
        int r2Idx = 0;
        int counter = 0;

        while (counter < arr.length) {
            if (r1Idx < result1.length && r2Idx < result2.length) {
                if (result1[r1Idx] < result2[r2Idx]) {
                    result[counter++] = result1[r1Idx++];
                } else {
                    result[counter++] = result2[r2Idx++];
                }
            } else if (r1Idx == result1.length) {
                result[counter++] = result2[r2Idx++];
            } else if (r2Idx == result2.length) {
                result[counter++] = result1[r1Idx++];
            }
        }

        return result;
    }

    private static int[] reverseMergeSort(int[] arr, int size) {
        if (size == 1) {
            return new int[] { arr[0] };
        }

        if (size == 2) {
            if (arr[0] < arr[1]) return new int[] { arr[1], arr[0] };
            else return new int[] { arr[0], arr[1] };
        }

        int half = size / 2;
        int[] part1;
        int[] part2;

        int[] result1;
        int[] result2;

        if ((size & 1) == 1) {
            part1 = new int[half];
            part2 = new int[half + 1];

            for (int i = 0; i < part1.length; i++) {
                part1[i] = arr[i];
            }

            for (int i = 0; i < part2.length; i++) {
                part2[i] = arr[half + i];
            }

            result1 = reverseMergeSort(part1, half);
            result2 = reverseMergeSort(part2, half + 1);

        } else {
            part1 = new int[half];
            part2 = new int[half];

            for (int i = 0; i < part1.length; i++) {
                part1[i] = arr[i];
                part2[i] = arr[half + i];
            }

            result1 = reverseMergeSort(part1, half);
            result2 = reverseMergeSort(part2, half);
        }

        int[] result = new int[arr.length];

        int r1Idx = 0;
        int r2Idx = 0;
        int counter = 0;

        while (counter < arr.length) {
            if (r1Idx < result1.length && r2Idx < result2.length) {
                if (result1[r1Idx] > result2[r2Idx]) {
                    result[counter++] = result1[r1Idx++];
                } else {
                    result[counter++] = result2[r2Idx++];
                }
            } else if (r1Idx == result1.length) {
                result[counter++] = result2[r2Idx++];
            } else if (r2Idx == result2.length) {
                result[counter++] = result1[r1Idx++];
            }
        }

        return result;
    }

}