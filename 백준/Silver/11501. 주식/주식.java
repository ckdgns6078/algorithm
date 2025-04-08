import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = sc.nextInt();

		for (int tc = 0; tc < testCase; tc++) {
			int n = sc.nextInt();

			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			int idx = n - 1;
			int max = 0;
			long answer = 0;
			while (idx >= 0) {
				if (arr[idx] > max) {
					max = arr[idx];
				} else {
					answer += max - arr[idx];
				}
				idx--;
			}
			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
