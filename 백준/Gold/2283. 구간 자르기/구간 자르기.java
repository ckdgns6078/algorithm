import java.util.*;

public class Main {
	static int N, K;
	static List<int[]> list = new ArrayList<>();
	static int[] cnt = new int[1_000_002]; // [0 ~ 1_000_001]
	static int[] sum = new int[1_000_002];
	static int maxCoord = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();
			list.add(new int[]{left, right});
			cnt[left] += 1;
			cnt[right] -= 1;
			maxCoord = Math.max(maxCoord, right);
		}

		// 누적합: cnt[i] → i번째 좌표에 걸쳐 있는 구간 수
		for (int i = 1; i <= maxCoord; i++) {
			cnt[i] += cnt[i - 1];
		}

		// 누적합: sum[i] → 0부터 i까지의 총 포함된 길이
		for (int i = 1; i <= maxCoord; i++) {
			sum[i] = sum[i - 1] + cnt[i];
		}

		// 투 포인터
		int left = 0, right = 1;
		boolean found = false;
		int ansLeft = 0, ansRight = 0;

		while (left < right && right <= maxCoord) {
			int total = sum[right - 1] - (left > 0 ? sum[left - 1] : 0);

			if (total == K) {
				ansLeft = left;
				ansRight = right;
				found = true;
				break;
			} else if (total < K) {
				right++;
			} else {
				left++;
				if (left >= right) right = left + 1;
			}
		}

		if (found) {
			System.out.println(ansLeft + " " + ansRight);
		} else {
			System.out.println("0 0");
		}
	}
}
