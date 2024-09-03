import java.util.*;

class Vertex implements Comparable<Vertex> {
	int e;
	int w;

	Vertex(int e, int w) {
		this.e = e;
		this.w = w;
	}

	public int compareTo(Vertex o) {
		return Integer.compare(this.w, o.w);
	}

}

public class Main {

	static boolean[] v;
	static ArrayList<Vertex>[] list;
	static int n, m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		list = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();

			list[start].add(new Vertex(end, weight));
			list[end].add(new Vertex(start, weight));
		}

		int v1 = sc.nextInt();
		int v2 = sc.nextInt();
		boolean check1 = true;
		boolean check2 = true;

		// 1 -> v1 -> v2 -> N-1
		int num11 = dijkstra(1, v1);
		int num12 = dijkstra(v1, v2);
		int num13 = dijkstra(v2, n);
		int sum1 = num11 + num12 + num13;

		// 1 -> v2 -> v1 -> N-1
		int num21 = dijkstra(1, v2);
		int num22 = dijkstra(v2, v1);
		int num23 = dijkstra(v1, n);
		int sum2 = num21 + num22 + num23;
		if (num11 == Integer.MAX_VALUE || num12 == Integer.MAX_VALUE || num13 == Integer.MAX_VALUE
				|| num21 == Integer.MAX_VALUE || num22 == Integer.MAX_VALUE || num23 == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			int result = Math.min(sum1, sum2);
			System.out.println(result);
		}

	}

	public static int dijkstra(int start, int end) {
		boolean[] v = new boolean[n + 1];
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Vertex> q = new PriorityQueue();
		q.offer(new Vertex(start, 0));
		dist[start] = 0;

		while (!q.isEmpty()) {
			Vertex p = q.poll();

			if (v[p.e]) {
				continue;
			}
			v[p.e] = true;

			for (Vertex next : list[p.e]) {
				if (!v[next.e] && next.w + dist[p.e] < dist[next.e]) {
					dist[next.e] = next.w + dist[p.e];
					q.offer(new Vertex(next.e, dist[next.e]));

				}
			}
		}
		return dist[end];
	}
}
