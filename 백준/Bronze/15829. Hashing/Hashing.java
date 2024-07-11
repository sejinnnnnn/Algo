import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static final int M = 1234567891;
    static final int r = 31;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();

        long answer = 0;

        for (int i = 0; i < n; i++) {
            long result = (long) ((str[i] - 'a' + 1) * Math.pow(r, i)) % M;
            answer += result;
            answer %= M;
        }

        System.out.println(answer);

    }

}