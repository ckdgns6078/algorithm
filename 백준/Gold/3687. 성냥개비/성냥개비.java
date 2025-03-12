import java.util.*;

public class Main {

	static int N;

	static long[] dp;
	static String[] temp = { "1", "7", "4", "2", "0", "8" };
	static StringBuilder sb = new StringBuilder();
	static int[] inputList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		inputList = new int[N];
		for (int i = 0; i < N; i++) {
			inputList[i] = sc.nextInt();
		}

		dp = new long[101];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		dp[8] = 10;

		for (int i = 9; i < 101; i++) {
			for (int j = 2; j <= 7; j++) {
				String line = dp[i - j] + temp[j - 2];
				dp[i] = Math.min(Long.parseLong(line), dp[i]);
			}
		}

		for (int i = 0; i < N; i++) {
			int findValue = inputList[i];
			sb.append(dp[findValue]).append(" ").append(findMax(findValue)).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static String findMax(int input) {
		StringBuilder maxSb = new StringBuilder();
		long cnt = input / 2;
		long result = input % 2;

		if (result == 1) {
			maxSb.append("7");
		} else {
			maxSb.append("1");
		}

		for (int i = 1; i < cnt; i++) {
			maxSb.append("1");
		}
		return maxSb.toString();
	}

}
