import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();
        
        int intResult = Integer.parseInt(A) + Integer.parseInt(B) - Integer.parseInt(C);
        
        String AB = A + B;
        
        int stringResult = Integer.parseInt(AB) - Integer.parseInt(C);
        
        System.out.printf("%d\n%d", intResult, stringResult);
        
    }
    
}