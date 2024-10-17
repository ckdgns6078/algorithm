import java.util.*;

class Electronic implements Comparable<Electronic> {
	int edge;
	int weight;

	public Electronic(int edge, int weight) {
		this.edge = edge;
		this.weight = weight;
	}
	@Override
	public int compareTo(Electronic o) {
		return Integer.compare(this.weight, o.weight);
	}

}

public class Main {

	static int n;
	static int m;
	static int k;
	static ArrayList<Electronic>[] list;
	static boolean[] visited;
	static int[] used;
	static int[] dist;
	static int[] powerPlants;
	static PriorityQueue<Electronic> q = new PriorityQueue();
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt(); // 도시의 개수
		m = sc.nextInt(); // 간선의 개수
		k = sc.nextInt(); // 발전소의 개수

		list = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		used = new int[n + 1];
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList();
		}
		powerPlants = new int[k];

		for (int i = 0; i < k; i++) {
			powerPlants[i] = sc.nextInt();
		}

		// 간선 추가
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			list[u].add(new Electronic(v, w));
			list[v].add(new Electronic(u, w));
		}

		HashSet<Integer> set = new HashSet();
		for (int i = 0; i < k; i++) {
			q.offer(new Electronic(powerPlants[i], 0));
			dist[powerPlants[i]] = 0;
			set.add(powerPlants[i]);
		}

		while (!q.isEmpty()) {
			Electronic p = q.poll();

			if (visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			result += p.weight;
			for (Electronic next : list[p.edge]) {
				if (!visited[next.edge] && next.weight < dist[next.edge]) {
					if (set.contains(p.edge) && set.contains(used[next.edge])) {
						continue;
					}
					dist[next.edge] = next.weight;
					used[p.edge] = next.edge;
					q.offer(next);
				}
			}
		}
		System.out.println(result);

	}

}
