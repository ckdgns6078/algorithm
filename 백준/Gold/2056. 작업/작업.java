
import java.util.*;

public class Main {

	static int N;
	static int[] times;
	static int[] indegree;
	static List<Integer>[] list;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		times = new int[N + 1];
		indegree = new int[N + 1];
		dp = new int[N + 1];
		list = new ArrayList[N + 1];

		// 입력부
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 1; i <= N; i++) {
			times[i] = sc.nextInt();
			int count = sc.nextInt();

			for (int j = 0; j < count; j++) {
				int pre = sc.nextInt();
				list[pre].add(i);
				indegree[i]++;
			}
		}

		// 연산부
		Queue<Integer> q = new ArrayDeque();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				dp[i] = times[i];
			}
		}

		while (!q.isEmpty()) {
			int p = q.poll();

			for (int next : list[p]) {
				dp[next] = Math.max(dp[next], dp[p] + times[next]);
				indegree[next]--;
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		int result = 0;
		for(int i=0;i<=N;i++) {
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
	}

}
