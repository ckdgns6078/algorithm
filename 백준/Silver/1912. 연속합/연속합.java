import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long result =Integer.MIN_VALUE;
		int n = sc.nextInt();
		long[] arr = new long[n+1];
		long[] dp = new long[n+1];
		for(int i = 1 ; i < n+1 ; i++) {
			arr[i] = sc.nextInt();
		}
		
		
		
		for(int i = 1 ; i < n+1 ; i++) {
			dp[i] = Math.max(arr[i] , dp[i-1] + arr[i]);
//			System.out.println("dp[ " + i + " ]"+dp[i]);
			result = Math.max(result, dp[i]);
		}
		
		System.out.println(result);
		
	}

}
