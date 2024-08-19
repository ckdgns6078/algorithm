import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			int n = sc.nextInt();
			int l = sc.nextInt();

			int[] score = new int[n];
			int[] cal = new int[n];

			for (int i = 0; i < n; i++) {
				score[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}

			int max = 0;

			for (int r = 1; r <= n; r++) {
				int[] subset = new int[n];
				for (int i = n - r; i < n; i++) {
					subset[i] = 1;
				}

				do {
					int totals = 0;
					int totalc = 0;

					for (int i = 0; i < n; i++) {
						if (subset[i] == 1) {
							totals += score[i];
							totalc += cal[i];
						}

					}
					if (totalc <= l) {
						max = Math.max(max, totals);
					}
				} while (permutation(subset));
			}
			System.out.println("#" + tc + " " + max);

		}

	}

	private static boolean permutation(int[] arr) {
		int len = arr.length;
		if (len <= 1) {
			return false;
		}

		int i = len - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0) {
			return false;
		}

		int j = len - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		swap(arr, i - 1, j);

		int k = len - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}

		return true;

	}

	private static void swap(int[] arr, int cnt, int cnt2) {
		int temp = arr[cnt];
		arr[cnt] = arr[cnt2];
		arr[cnt2] = temp;
	}
}
