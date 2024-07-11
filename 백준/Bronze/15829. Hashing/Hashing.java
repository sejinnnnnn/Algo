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
        long pow = 1;

        for (int i = 0; i < n; i++) {
            long result = ((str[i] - 'a' + 1) * pow) % M;
            answer += result;
            answer %= M;

            pow = (pow * r) % M;
        }

        System.out.println(answer % M);

    }

}