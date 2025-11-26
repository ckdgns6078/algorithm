import java.util.*;

public class Main {

	static int N;
	static int M;
	static int K;

	static int[] candy;
	static List<Integer>[] list;
	static boolean[] visited;

	static List<Integer> lenList;
	static List<Integer> sumList;
	static int groupCnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		list = new ArrayList[N + 1];
		candy = new int[N + 1];
		visited = new boolean[N + 1];

		lenList = new ArrayList();
		sumList = new ArrayList();

		for (int i = 1; i <= N; i++) {
			candy[i] = sc.nextInt();
			list[i] = new ArrayList();
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}

		for (int i = 1; i < list.length; i++) {
			if (!visited[i]) {
				bfs(i);
				groupCnt++;
			}
		}

		int[] dp = new int[K];
		// 큰 숫자부터 가져온다.

		for (int i = 0; i < groupCnt; i++) {

			int size = lenList.get(i);
			int sum = sumList.get(i);

			if (size >= K) {
				continue;
			}

			for (int j = K - 1; j >= size; j--) {
				dp[j] = Math.max(dp[j], dp[j - size] + sum);
			}
		}

		int answer =0;
		for(int i : dp) {
			answer = Math.max(answer, i);
		}
		System.out.println(answer);
	}

	public static void bfs(int start) {

		Queue<Integer> q = new ArrayDeque();
		q.offer(start);
		visited[start] = true;

		int sum = 0;
		int cnt = 0;
		while (!q.isEmpty()) {
			int p = q.poll();

			sum += candy[p];
			cnt++;

			for (int idx : list[p]) {
				if (visited[idx]) {
					continue;
				}

				q.offer(idx);
				visited[idx] = true;
			}
		}

		sumList.add(sum);
		lenList.add(cnt);
	}
}
