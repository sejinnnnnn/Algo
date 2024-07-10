import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 예를 들어 고유번호의 처음 5자리의 숫자들이 04256이면, 
// 각 숫자를 제곱한 수들의 합 0+16+4+25+36 = 81 을 10으로 나눈 나머지인 1이 검증수이다.
public class Main {
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        
        int sum = 0;
        
        for (int i = 0; i < 5; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num * num;
        }
        
        System.out.println(sum % 10);
    }
}