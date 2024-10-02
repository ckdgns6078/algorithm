
import java.util.*;

public class Main  {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String input1 = sc.next();
		String input2 = sc.next();
		int answer = 0;
		char[] arr1 = new char[input1.length() + 1];
		char[] arr2 = new char[input2.length() + 1];

		for (int i = 1; i < arr1.length; i++) {
			arr1[i] = input1.charAt(i - 1);
		}

		for (int i = 1; i < arr2.length; i++) {
			arr2[i] = input2.charAt(i - 1);
		}
		
		int[][] dp = new int[1119][1119];
		
		for (int i = 1; i < arr2.length; i++) {
			for (int j = 1; j < arr1.length; j++) {
				if (arr2[i] == arr1[j]) {
					dp[i][j] = dp[i-1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				answer = Math.max(answer, dp[i][j]);
			}
		}
		System.out.println(dp[arr2.length-1][arr1.length-1]);

	}

}
