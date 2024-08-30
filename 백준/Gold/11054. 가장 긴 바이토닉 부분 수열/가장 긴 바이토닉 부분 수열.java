import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] dp1 = new int[n + 1];
		int[] dp2 = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			Arrays.fill(dp1, 1);
			Arrays.fill(dp2, 1);
		}

		for (int i = 1; i < n; i++) {  // i기준 최장 증가 부분 수열 
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp1[i] = Math.max(dp1[i], dp1[j] + 1);
				}
			}
		}
		
		for (int i = n - 1; i >= 0; i--) {  // i기준 최장 감소 부분 수열
			for (int j = n - 1; j > i; j--) {
				if (arr[i] > arr[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}
		
		int max = 1;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, dp1[i] + dp2[i]);  // 가장 긴 바이토닉 수열 = max(dp1[i] + dp2[i])
		}
		
		System.out.println(max - 1);
	}
}