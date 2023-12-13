import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int i = Integer.parseInt(st.nextToken());
        int j = Integer.parseInt(st.nextToken());

        int hCount = (int) Math.ceil(1.0 * height / (i + 1));
        int wCount = (int) Math.ceil(1.0 * width / (j + 1));

        System.out.println(hCount * wCount);
    }

}