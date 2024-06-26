import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder answer = new StringBuilder();
        
        for (int i = N; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                answer.append('*');
            }
            answer.append("\n");
        }
        
        System.out.println(answer.toString().trim());
        
    }
}