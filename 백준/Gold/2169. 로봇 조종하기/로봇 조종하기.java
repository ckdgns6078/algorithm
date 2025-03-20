import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] dp;
	static int[][] left;
	static int[][] right;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		dp = new int[N][M];
		left = new int[N][M];
		right = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

			}
		}

		dp[0][0] = map[0][0];

		for (int i = 1; i < M; i++) {
			dp[0][i] = dp[0][i-1] + map[0][i];
		}

		for (int i = 1; i < N; i++) {

			// 왼쪽에서 오른쪽
			for (int j = 0; j < M; j++) {
				if (j == 0) {
					left[i][j] = dp[i - 1][j] + map[i][j];
				} else {
					left[i][j] = Math.max(dp[i - 1][j], left[i][j - 1]) + map[i][j];
				}
			}

			//오른쪽에서 왼쪽
			for (int j = M - 1; j >= 0; j--) {
				if (j == M - 1) {
					right[i][j] = dp[i - 1][j] + map[i][j];
				} else {
					right[i][j] = Math.max(dp[i - 1][j], right[i][j + 1]) + map[i][j];
				}
			}

			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(left[i][j], right[i][j]);
			}

		}

		System.out.println(dp[N - 1][M - 1]);

	}
}