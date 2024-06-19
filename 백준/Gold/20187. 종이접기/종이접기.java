import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static boolean[][] paper;
    static char[] foldings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        k = Integer.parseInt(br.readLine());
        foldings = new char[2 * k];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * k; i++) {
            foldings[2 * k - 1 - i] = st.nextToken().charAt(0);
        }

        paper = new boolean[2][2];

        int hole = Integer.parseInt(br.readLine());

        if (hole == 0) paper[0][0] = true;
        else if (hole == 1) paper[0][1] = true;
        else if (hole == 2) paper[1][0] = true;
        else if (hole == 3) paper[1][1] = true;

        for (int i = 0; i < 2 * k; i++) {
            expandPaper(foldings[i]);

//            for (int j = 0; j < paper.length; j++) {
//                System.out.println(Arrays.toString(paper[j]));
//            }
        }

        for (int i = 0; i < paper.length / 2; i++) {
            for (int j = 0; j < paper.length / 2; j++) {
                if (paper[i * 2][j * 2]) answer.append(0);
                else if (paper[i * 2][j * 2 + 1]) answer.append(1);
                else if (paper[i * 2 + 1][j * 2]) answer.append(2);
                else if (paper[i * 2 + 1][j * 2 + 1]) answer.append(3);
                if (j != paper.length / 2 - 1) answer.append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer.toString().trim());

    }

    private static void expandPaper(char direction) {

        // 크기 늘린 2차원 배열 선언
        boolean[][] newPaper = null;

//        System.out.println(paper.length);

        if (direction == 'D') {
            newPaper = new boolean[paper.length * 2][paper.length];

//            System.out.println(paper.length * 2);

            // 원래 부분 채우기
            for (int i = 0; i < paper.length; i++) {
                newPaper[i + paper.length] = Arrays.copyOf(paper[i], paper[i].length);
            }

            // 새 부분 채우기 (절반 선 기준으로 행 선대칭)
            for (int i = 0; i < paper.length; i++) {
                newPaper[i] = Arrays.copyOf(paper[paper.length - 1 - i], paper[0].length);
            }
        } else if (direction == 'U') {
            newPaper = new boolean[paper.length * 2][paper.length];

            // 원래 부분 채우기
            for (int i = 0; i < paper.length; i++) {
                newPaper[i] = Arrays.copyOf(paper[i], paper[i].length);
            }

            // 새 부분 채우기 (절반 선 기준으로 행 선대칭)
            for (int i = 0; i < paper.length; i++) {
                newPaper[paper.length + i] = Arrays.copyOf(paper[paper.length - 1 - i], paper[0].length);
            }

        } else if (direction == 'R') {
            newPaper = new boolean[paper.length][paper[0].length * 2];

//            System.out.println(newPaper.length + " " + newPaper[0].length);

            // 원래 부분 채우기
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
//                    System.out.println(i + " " + j);
                    newPaper[i][j + paper[0].length] = paper[i][j];
                }
            }

            // 새 부분 채우기 (절반 선 기준으로 열 선대칭)
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    newPaper[i][j] = paper[i][paper[0].length - 1 - j];
                }
            }
            
        } else if (direction == 'L') {
            newPaper = new boolean[paper.length][paper[0].length * 2];

            // 원래 부분 채우기
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    newPaper[i][j] = paper[i][j];
                }
            }

            // 새 부분 채우기 (절반 선 기준으로 열 선대칭)
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    newPaper[i][j + paper[0].length] = paper[i][paper[0].length - 1 - j];
                }
            }
        }

        paper = newPaper;
    }

}