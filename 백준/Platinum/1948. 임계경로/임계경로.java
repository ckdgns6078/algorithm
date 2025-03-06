import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int to, time;

		Edge(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	static int n, m, start, end;
	static List<Edge>[] graph, reverseGraph;
	static int[] inDegree, dist;
	static boolean[] visited;
	static List<int[]> criticalEdges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];
		reverseGraph = new ArrayList[n + 1];
		inDegree = new int[n + 1];
		dist = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			graph[u].add(new Edge(v, t));
			reverseGraph[v].add(new Edge(u, t));
			inDegree[v]++;
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		int longestTime = topologicalSort();

		int criticalPathCount = findCriticalPath();

		System.out.println(longestTime);
		System.out.println(criticalPathCount);
	}

	private static int topologicalSort() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge edge : graph[cur]) {
				int next = edge.to;
				if (dist[next] < dist[cur] + edge.time) {
					dist[next] = dist[cur] + edge.time;
				}
				if (--inDegree[next] == 0) {
					q.add(next);
				}
			}
		}
		return dist[end];
	}

	private static int findCriticalPath() {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[n + 1];
		q.add(end);
		visited[end] = true;
		int count = 0;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge edge : reverseGraph[cur]) {
				int prev = edge.to;

				if (dist[cur] - edge.time == dist[prev]) {
					count++;
					if (!visited[prev]) {
						visited[prev] = true;
						q.add(prev);
					}
				}
			}
		}
		return count;
	}
}
