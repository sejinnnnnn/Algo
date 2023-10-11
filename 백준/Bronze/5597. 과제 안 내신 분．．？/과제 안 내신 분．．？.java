import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] submitted = new boolean[30];
        for (int i = 0; i < 28; i++) {
            submitted[Integer.parseInt(br.readLine()) - 1] = true;
        }
        
        for (int i = 0; i < 30; i++) {
            if (!submitted[i]) System.out.println((i + 1));
        }
        
    }
    
}