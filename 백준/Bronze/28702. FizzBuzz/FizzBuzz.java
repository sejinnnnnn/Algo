import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static final String FIZZ = "Fizz";
    static final String BUZZ = "Buzz";
    static final String FIZZBUZZ = "FizzBuzz";


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = 0;
        int idx = 0;

        for (int i = 0; i < 3; i++) {
            String str = br.readLine();
            if (!str.equals(FIZZ) && !str.equals(BUZZ) && !str.equals(FIZZBUZZ)) {
                number = Integer.parseInt(str);
                idx = i;
                break;
            }
        }

        int answer = number + 3 - idx;

        if (answer % 3 == 0 && answer % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (answer % 3 == 0) {
            System.out.println(FIZZ);
        } else if (answer % 5 == 0) {
            System.out.println(BUZZ);
        } else {
            System.out.println(answer);
        }

    }

}