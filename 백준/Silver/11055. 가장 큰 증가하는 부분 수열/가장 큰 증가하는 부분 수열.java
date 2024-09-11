
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] arr = new long[n + 1];
		long[] dp = new long[1500];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = sc.nextInt();
		}
		dp[1] = arr[1];
		long result = arr[1];

		for (int i = 2; i < n + 1; i++) {
			long cnt = 0;
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j]) {
					cnt = Math.max(cnt, dp[j]);
				}
			}
			dp[i] = cnt + arr[i];
//			System.out.println("dp [" + i + "] :" + dp[i] );
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
	}

}
