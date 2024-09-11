import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t = 0 ; t <T ; t++) {
			int n = sc.nextInt();
		
			long[] dp = new long[1000];
			
			dp[1] =1;
			dp[2] =1;
			for(int i=3; i < n+1 ;i++) {
				dp[i] = dp[i-2] + dp[i-3];
			}
			System.out.println(dp[n]);
		}
		
	}

}
