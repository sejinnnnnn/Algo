import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int moveCount;

    static int[][] map;
    static int[][] moves;
    static int moveIdx = 0;

    static ArrayDeque<int[]> snake = new ArrayDeque<>();
    static int direction = 0;

    static final int[] dx = { 0, 1, 0, -1 };
    static final int[] dy = { 1, 0, -1, 0 };


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int appleCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < appleCount; i++) {
            st = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(st.nextToken()) - 1;
            int appleY = Integer.parseInt(st.nextToken()) - 1;
            map[appleX][appleY] = 2; // 사과 위치 표시
        }

        moveCount = Integer.parseInt(br.readLine());
        moves = new int[moveCount][2];

        for (int i = 0; i < moveCount; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            switch (d) {
                case 'D':
                    moves[i][1] = 1;
                    break;
                case 'L':
                    moves[i][1] = -1;
                    break;
            }
        }

        snake.addFirst(new int[] { 0, 0 });
        int time;

        for (time = 0; expandHead(time); time++) {
//            System.out.println(time);
//            for (int[] m : map) {
//                System.out.println(Arrays.toString(m));
//            }

        }

        System.out.println(++time);

    }

    private static boolean expandHead(int time) {

        if (moveIdx < moveCount && moves[moveIdx][0] == time) {
            direction += moves[moveIdx++][1];
            if (direction == -1) direction = 3;
            else if (direction == 4) direction = 0;
        }

        int[] head = snake.peekFirst();

        int newX = head[0] + dx[direction];
        int newY = head[1] + dy[direction];

        // 새로운 머리가 인덱스 범위를 벗어나면 벽과 충돌한 것이므로 false 반환
        if (!isInIndex(newX, newY)) return false;

        // 새로운 머리가 map을 기준으로 값이 1일 때는 자기 자신과 부딪힌 것이므로 false 반환
        if (map[newX][newY] == 1) return false;

        // 새로운 머리가 사과가 아니라면 꼬리를 줄임
        if (map[newX][newY] != 2) reduceTail();

        // 새로 만든 값 snake 큐의 맨 앞에 추가하기
        snake.addFirst(new int[] { newX, newY });
        map[newX][newY] = 1;

        return true;
    }

    private static void reduceTail() {
        int[] tail = snake.pollLast();
        map[tail[0]][tail[1]] = 0;
    }


    private static boolean isInIndex(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}