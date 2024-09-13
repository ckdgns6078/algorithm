import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		long[][] dp = new long[1100][11];
		
		for(int i = 1 ; i < 11 ;i++) {
			dp[1][i] = 1;
		}
		
		
		for(int i = 2 ; i < n+1 ; i++) {
			
			for(int j=1 ; j < 11 ; j++) {
				for(int k=1 ; k <=j ; k++) {
					dp[i][j] = (dp[i][j] + dp[i-1][k]) % 10007;
				}
				
			}
//			System.out.println("dp[" + i + "]  "+ Arrays.toString(dp[i]));
		}
		
		
		
		long result = 0;
		for(int i = 1 ; i < 11 ; i++) {
			result = (result + dp[n][i]) % 10007;
		}
		System.out.println(result);
		

	}

}
