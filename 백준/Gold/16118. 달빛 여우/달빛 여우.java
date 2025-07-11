import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int m;
	static int[] foxDist;
	static int[][] wolfDist;
	static List<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		foxDist = new int[n + 1];
		wolfDist = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
			foxDist[i] = Integer.MAX_VALUE;
			wolfDist[i][0] = Integer.MAX_VALUE;
			wolfDist[i][1] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()) * 2;
			list[start].add(new Node(end, cost));
			list[end].add(new Node(start, cost));
		}

		foxDist[1] = 0;
		wolfDist[1][0] = 0;

		foxDijkstra();
		wolfDijkstra();

		int answer = 0;
		for (int i = 2; i <= n; i++) {
			if (foxDist[i] < Math.min(wolfDist[i][0], wolfDist[i][1])) {
				answer++;
			}
		}
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void foxDijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (curr.cost > foxDist[curr.n]) continue;

			for (Node next : list[curr.n]) {
				int newCost = curr.cost + next.cost;
				if (newCost < foxDist[next.n]) {
					foxDist[next.n] = newCost;
					pq.offer(new Node(next.n, newCost));
				}
			}
		}
	}

	public static void wolfDijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (curr.cost > wolfDist[curr.n][curr.value]) continue;

			for (Node next : list[curr.n]) {
				int nextValue = 1 - curr.value;
				int nextCost = curr.value == 0
						? curr.cost + (next.cost / 2)
						: curr.cost + (next.cost * 2);

				if (nextCost < wolfDist[next.n][nextValue]) {
					wolfDist[next.n][nextValue] = nextCost;
					pq.offer(new Node(next.n, nextCost, nextValue));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int n;
		int cost;
		int value;

		Node(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}

		Node(int n, int cost, int value) {
			this.n = n;
			this.cost = cost;
			this.value = value;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
