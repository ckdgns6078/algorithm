import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int n = sc.nextInt();

			int[][] dp = new int[4][n + 1];

			Arrays.fill(dp[1], 1);

			for (int i = 2; i < 4; i++) {
				for (int j = 1; j < n + 1; j++) {
					if (j == i) {
						dp[i][j] = dp[i - 1][j] + 1;
					} else if (j > i) {
						dp[i][j] = dp[i - 1][j] + dp[i][j - i];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			sb.append(dp[3][n]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
