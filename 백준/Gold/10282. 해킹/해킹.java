import java.util.*;

public class Main {

	static List<Node>[] graph;
	static int[] dist;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 0; t < tc; t++) {

			int n = sc.nextInt(); // 컴퓨터 개수
			int d = sc.nextInt(); // 의존성 개수
			int c = sc.nextInt(); // 해킹당한 컴퓨터 번호

			graph = new ArrayList[n + 1];
			dist = new int[n + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);

			for (int i = 1; i < n + 1; i++) {
				graph[i] = new ArrayList();
			}

			for (int i = 0; i < d; i++) {
				int scom = sc.nextInt();
				int ecom = sc.nextInt();
				int cost = sc.nextInt();

				graph[ecom].add(new Node(scom, cost));
			}

			dijkstra(c);
			
			int answer = 0;
			int temp =0;
			for(int cnt : dist) {
				
				if(cnt ==Integer.MAX_VALUE) continue;
				temp++;
				answer=  Math.max(answer, cnt);
			}
			
			System.out.println(temp + " " + answer);
		}

	}

	public static void dijkstra(int start) {
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			int now = n.idx;
			int cost = n.cost;

			if (cost > dist[now])
				continue;

			for (Node next : graph[now]) {
				int newCost = cost + next.cost;
				if (newCost < dist[next.idx]) {
					dist[next.idx] = newCost;
					pq.offer(new Node(next.idx, newCost));
				}

			}
		}
	}

	static class Node implements Comparable<Node> {
		int idx;
		int cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
