import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[] time;
	static long answer = Long.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		time = new int[N];

		for (int i = 0; i < N; i++) {
			time[i] = sc.nextInt();
		}

		Arrays.sort(time);

		long left = 0;
		long right = (long) M * time[N - 1];

		while (left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;

			for (int i = 0; i < time.length; i++) {
				cnt += mid / time[i];
				if (cnt >= M) break;
			}

			if (cnt >= M) {
				answer = Math.min(answer, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);
	}
}
