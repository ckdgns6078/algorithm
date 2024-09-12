import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		long[] dp = new long[1100000];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3 ; i < n+1 ; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 15746;
		}
		
		System.out.println(dp[n]);
	}

}
