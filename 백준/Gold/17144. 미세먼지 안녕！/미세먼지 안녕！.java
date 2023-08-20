import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][col];
        int dustCount = 2; // 공기청정기 두 개의 -1이 같이 더해지기 때문에 2로 초기화
        int cleaner = -1;

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (cleaner == -1 && map[i][j] == -1) {
                    cleaner = i;
                }
                dustCount += map[i][j];
            }
        }

        for (int t = 0; t < time; t++) {

            // 미세먼지 확산
            int[][] nextMap = new int[row][col];

            // 공기청정기 위치 값 -1 넣어주기
            nextMap[cleaner][0] = -1;
            nextMap[cleaner + 1][0] = -1;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // 전의 map을 보면서 만약 공기청정기가 아니면서 미세먼지가 있으면
                    if (map[i][j] != 0 && map[i][j] != -1) {
                        // 확산이 몇 번 이뤄졌는지 저장하는 값
                        int spreadCount = 0;
                        int spreadValue = map[i][j] / 5;

                        // 확산 진행 (확산이 동시에 일어나기 때문에, 이전의 map 값을 기준으로 nextMap에 더해줘야 함 !)
                        for (int k = 0; k < 4; k++) {
                            int nextX = i + dx[k];
                            int nextY = j + dy[k];

                            // 범위 체크 후 범위 내에 있고 그 칸이 공기청정기가 아니라면
                            if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && map[nextX][nextY] != -1) {
                                nextMap[nextX][nextY] += spreadValue;
                                spreadCount++;
                            }
                        }

                        // nextMap에 기존 맵의 원래 인덱스 값에 spreadCount만큼의 먼지 양을 빼준다
                        nextMap[i][j] += map[i][j] - (spreadValue * spreadCount);
                    }
                }
            }

//            for (int[] arr: nextMap) {
//                System.out.println(Arrays.toString(arr));
//            }
//
//            System.out.println();

            // 공기청정기 청소

            // 첫 번째 칸 : 반시계 방향으로 한 칸씩 밀기
            int colTemp = nextMap[cleaner][col - 1];
            for (int i = col - 1; i >= 2; i--) {
                nextMap[cleaner][i] = nextMap[cleaner][i - 1];
            }
            nextMap[cleaner][1] = 0;
            int rowTemp = nextMap[0][col - 1];
            for (int i = 0; i < cleaner - 1; i++) {
                nextMap[i][col - 1] = nextMap[i + 1][col - 1];
            }
            nextMap[cleaner - 1][col - 1] = colTemp;
            colTemp = nextMap[0][0];
            for (int i = 0; i < col - 1; i++) {
                nextMap[0][i] = nextMap[0][i + 1];
            }
            nextMap[0][col - 2] = rowTemp;
            for (int i = cleaner - 1; i > 0; i--) {
                nextMap[i][0] = nextMap[i - 1][0];
            }
            nextMap[1][0] = colTemp;

            // 두 번째 칸 : 시계 방향으로 한 칸씩 밀기
            colTemp = nextMap[cleaner + 1][col - 1];
            for (int i = col - 1; i >= 2; i--) {
                nextMap[cleaner + 1][i] = nextMap[cleaner + 1][i - 1];
            }
            nextMap[cleaner + 1][1] = 0;
            rowTemp = nextMap[row - 1][col - 1];
            for (int i = row - 1; i > cleaner + 1; i--) {
                nextMap[i][col - 1] = nextMap[i - 1][col - 1];
            }
            nextMap[cleaner + 2][col - 1] = colTemp;
            colTemp = nextMap[row - 1][0];
            for (int i = 0; i < col - 1; i++) {
                nextMap[row - 1][i] = nextMap[row - 1][i + 1];
            }
            nextMap[row - 1][col - 2] = rowTemp;
            for (int i = cleaner + 2; i < row - 1; i++) {
                nextMap[i][0] = nextMap[i + 1][0];
            }
            nextMap[row - 2][0] = colTemp;

//            for (int[] arr: nextMap) {
//                System.out.println(Arrays.toString(arr));
//            }

            // 완성된 1초 후 nextMap을 기존 map 변수에 대입 + dustCount 재 계산 후 대입
            dustCount = 2;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    dustCount += nextMap[i][j];
                }
            }

            map = nextMap;

        }

        System.out.println(dustCount);


    }

}