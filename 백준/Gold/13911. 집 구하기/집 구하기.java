import java.util.*;

public class Main {

	static int V; // 정점의 개수
	static int E; // 도로의 개수

	static int M; // 맥도날드의 수
	static int x; // 맥세권일 조건

	static int S; // 스타벅스의 수
	static int y; // 스타벅스 조건 수

	static long[] distM;
	static long[] distS;

	static List<Node>[] graph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		graph = new ArrayList[V + 1];
		distM = new long[V + 1];
		Arrays.fill(distM, Long.MAX_VALUE);
		distS = new long[V + 1];
		Arrays.fill(distS, Long.MAX_VALUE);

		for (int i = 1; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}

		M = sc.nextInt();
		x = sc.nextInt();
		List<Integer> mecList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			mecList.add(sc.nextInt());
		}

		S = sc.nextInt();
		y = sc.nextInt();
		List<Integer> starList = new ArrayList<>();
		for (int i = 0; i < S; i++) {
			starList.add(sc.nextInt());
		}

		dikstra(distM, mecList);
		dikstra(distS, starList);

		long answer = Long.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			long mec = distM[i];
			long star = distS[i];

			if (mec == 0 || star == 0) continue;

			if (mec <= x && star <= y) {
				answer = Math.min(mec + star, answer);
			}
		}

		System.out.println(answer == Long.MAX_VALUE ? -1 : answer);
	}

	public static void dikstra(long[] dist, List<Integer> storeList) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int store : storeList) {
			dist[store] = 0;
			pq.offer(new Node(store, 0));
		}

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.idx;
			long cost = node.cost;

			if (cost > dist[now]) continue;

			for (Node next : graph[now]) {
				long newCost = cost + next.cost;
				if (newCost < dist[next.idx]) {
					dist[next.idx] = newCost;
					pq.offer(new Node(next.idx, newCost));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int idx;
		long cost;

		public Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
}
