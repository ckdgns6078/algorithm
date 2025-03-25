import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			int testCase = sc.nextInt();
			int[] arr = new int[20];
			int answer = 0;
			for (int i = 0; i < 20; i++) {
				arr[i] = sc.nextInt();
			}

			for (int i = 1; i < 20; i++) {
				int idx = Integer.MAX_VALUE;
				int temp = arr[i];

				for (int j = 0; j < i; j++) {
					if (arr[j] > arr[i]) {
						idx = j;
						break;
					}
				}

				if (idx != Integer.MAX_VALUE) {
					for (int k = i; k > idx; k--) {
						arr[k] = arr[k - 1];
					}
					arr[idx] = temp;
					answer += (i - idx);
				}

			}
			sb.append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
