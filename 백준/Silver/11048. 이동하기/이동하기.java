import java.util.*;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] arr = new int[n+1][m+1];
		int[][] dp = new int[n+1][m+1];
		
		for(int i = 1 ; i < n+1 ; i++) {
			for(int j = 1 ; j < m+1 ; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		dp[1][1] = arr[1][1];
		
		
		/*
		 * <- 0 ,-1
		 * 대각선 위 -1 ,-1
		 * 위 -1 , 0
		 */
		
		for(int i = 1 ; i < n+1 ; i++) {
			for(int j = 1 ; j < m+1 ; j++) {
				int maxValue = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
				dp[i][j] = maxValue + arr[i][j];
			}
		}
		
		
		System.out.println(dp[n][m]);
		
	}

}
