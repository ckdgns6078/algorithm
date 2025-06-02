import java.util.*;

public class Main {

	static int n;
	static int m;
	static List<int[]>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		n = sc.nextInt();
		m = sc.nextInt();

		list = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int pay = sc.nextInt();

			list[start].add(new int[]{end, pay});
			list[end].add(new int[]{start, pay});
		}

		for (int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			boolean[] visited = new boolean[n + 1];
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[]{start, 0});
			visited[start] = true;

			while (!q.isEmpty()) {
				int[] p = q.poll();
				int idx = p[0];
				int pay = p[1];

				if (idx == end) {
					sb.append(pay).append("\n");
					break;
				}

				for (int j = 0; j < list[idx].size(); j++) {
					int next = list[idx].get(j)[0];
					int cost = list[idx].get(j)[1];
					if (!visited[next]) {
						visited[next] = true;
						q.offer(new int[]{next, pay + cost});
					}
				}
			}
		}

		System.out.print(sb);
	}
}
