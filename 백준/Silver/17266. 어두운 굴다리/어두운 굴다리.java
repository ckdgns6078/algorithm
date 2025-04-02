import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[] arr;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		int left = 0;
		int right = N;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid)) {
				right = mid - 1;
				answer = mid;
			} else {
				left = mid + 1;
			}

			// 숫자가 들어 있는지 범위 검사를 해야되는가?

		}
		System.out.println(answer);

	}

	public static boolean check(int h) {
		int prev = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] - h <= prev) {
				prev = arr[i] + h;
			} else {
				return false;
			}
		}

		// 마지막 가로등이 비추는 곳이 굴다리 길이보다 같거나 커야함
		return N - prev <= 0;
	}

}
