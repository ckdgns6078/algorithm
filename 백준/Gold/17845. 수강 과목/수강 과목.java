import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int time = sc.nextInt();
		int K = sc.nextInt();

		int[] imp = new int[K];
		int[] need = new int[K];

		for (int i = 0; i < K; i++) {
			imp[i] = sc.nextInt();
			need[i] = sc.nextInt();
		}

		int[][] dp = new int[K][time + 1];

		for (int j = need[0]; j <= time; j++) {
			dp[0][j] = imp[0];
		}

		for (int i = 1; i < K; i++) {
			for (int j = 0; j <= time; j++) {
				if (j < need[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - need[i]] + imp[i]);
				}
			}
		}

		System.out.println(dp[K - 1][time]);
	}
}
