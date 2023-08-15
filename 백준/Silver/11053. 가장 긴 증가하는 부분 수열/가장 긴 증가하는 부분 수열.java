import java.util.Scanner;
/**
 * <pre>
 * 백준 11053번 : 가장 긴 증가하는 부분 수열 문제풀이
 * </pre>
 * 
 * @author sejinnnnnn
 * @version ver.1.0
 * @since JDK1.8
 */
public class Main {

   /**
    * @param None
    */
	public static void main(String[] args) {
      
      Scanner s = new Scanner(System.in);
      
      int n = s.nextInt();
      int arr[] = new int[n];
      int dp[] = new int[n];
      
      for (int i = 0; i < n; i++) {
         arr[i] = s.nextInt();
         dp[i] = 1;
      }
      
      int max = 0;
      
      for (int i = 0; i < n; i++) {
         
         for (int j = 0; j < i; j++) {
            
            if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
               dp[i] = dp[j] + 1;
               
            }
            
         }
         
         if (dp[i] > max) {
            max = dp[i];
         }
         
      }
      
      System.out.println(max);
      
      
      s.close();
      
   }

}