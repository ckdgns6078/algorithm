import java.util.*;
public class Main {
	static int n;
	static int m;
	static int[] memory;
	static int[] vite;
	static int[][] dp;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		memory = new int[n + 1];
		vite = new int[n + 1];
		long sum = 0;
		for (int i = 1; i < n + 1; i++) {
			memory[i] = sc.nextInt();
		}

		for (int i = 1; i < n + 1; i++) {
			vite[i] = sc.nextInt();
		}

		dp = new int[n + 1][10001];
		int temp = 0;
		for (int i = 1; i < n + 1; i++) {
			temp = 0;
			for (int j = 0; j <=10000; j++) {
				if (vite[i] == 0) {
					dp[i][j] = dp[i - 1][j] + memory[i];
					
				} else if (j >= vite[i]) { 
					dp[i][j] = Math.max(dp[i-1][j - vite[i]] + memory[i], dp[i - 1][j]);
					
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				if(dp[i][j]>=m) {
					answer = Math.min(answer, j);
				}
			}
		}
		System.out.println(answer);

	}

}
