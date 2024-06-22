import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        char[] word = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();

        int sum = 0;

        for (int i = 0; i < word.length; i++) {
            if (word[i] >= 'a' && word[i] <= 'z') {
                sum += word[i] - 'a' + 1;
            } else if (word[i] >= 'A' && word[i] <= 'Z') {
                sum += word[i] - 'A' + 27;
            }
        }

        if (sum == 1) System.out.println("It is a prime word.");
        else {
            boolean[] isNotPrime = new boolean[sum + 1];

            for (int i = 2; i <= sum; i++) {
                if (!isNotPrime[i]) {
                    int num = i * 2;
                    while (num <= sum) {
                        isNotPrime[num] = true;
                        num += i;
                    }
                }
            }

//            System.out.println(sum + " " + Arrays.toString(isNotPrime));

            if (isNotPrime[sum]) System.out.println("It is not a prime word.");
            else System.out.println("It is a prime word.");
        }


    }

}