import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[] oilBank;

	static List<Node>[] list;
	static int[][] dist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		oilBank = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			oilBank[i] = sc.nextInt();
		}

		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int di = sc.nextInt();

			list[start].add(new Node(end, di));
			list[end].add(new Node(start, di));
		}

		dist = new int[N + 1][2501];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dikjstra(1);

		int answer = Integer.MAX_VALUE;
		for (int cnt : dist[N]) {
			answer = Math.min(answer, cnt);
		}
		System.out.println(answer);

	}

	public static void dikjstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue();

		pq.offer(new Node(start, 0, oilBank[start]));

		while (!pq.isEmpty()) {

			Node node = pq.poll();
			int now = node.idx;
			int oilSum = node.oilSum;
			int min = node.min;

			if (oilSum > dist[now][min])
				continue;

			for (Node next : list[now]) {
				/**
				 * min 값을 갱신한다. min 값에 도착한 cost를 추가한다. 이 cost 비용이 더 비쌀 경우 스킵하고 더 낮을 경우 pq에 넣는다.
				 */
				int newOilSum = oilSum + (min * next.dist);
				int newMin = Math.min(min, oilBank[next.idx]);
				if (newOilSum < dist[next.idx][newMin]) {
					pq.offer(new Node(next.idx, newOilSum, newMin));
					dist[next.idx][newMin] = newOilSum;
				}

			}
		}

	}

	static class Node implements Comparable<Node> {
		int idx;
		int oilSum;
		int dist;
		int min;

		public Node(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		public Node(int idx, int oilSum, int min) {
			this.idx = idx;
			this.oilSum = oilSum;
			this.min = min;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.oilSum, o.oilSum);
		}
	}
}
