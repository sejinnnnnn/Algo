import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static long A;
    static long B;
    static long divider;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        divider = Long.parseLong(st.nextToken());

        System.out.println(multiply(B));

    }

    private static long multiply(long count) {
        if (count == 1) return A % divider;
        else if (count == 2) return (A * A) % divider;
        else {
            long mul = multiply(count / 2);
            if ((count & 1) == 0) return (mul * mul) % divider;
            else return ((mul * mul) % divider * A) % divider;
        }
    }

}