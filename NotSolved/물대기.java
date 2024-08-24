package 백준;

import java.util.*;

class Vertex4 implements Comparable<Vertex4> {
	int edge;
	int weight;

	Vertex4(int edge, int weight) {
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex4 o) {
		return Integer.compare(this.weight, o.weight);
	}

}

public class 물대기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] w = new int[n];
		int[][] map = new int[n][n];
		int result = 0;
		ArrayList<Vertex4>[] list = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < n; i++) {
			w[i] = sc.nextInt();
		}
		int sum = w[0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (i != j) {
					list[i].add(new Vertex4(j, map[i][j]));
					list[j].add(new Vertex4(i, map[i][j]));
				}
			}
		}

		PriorityQueue<Vertex4> pq = new PriorityQueue<Vertex4>();
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new Vertex4(0, 0));
		dist[0] = 0;

		while (!pq.isEmpty()) {
			Vertex4 p = pq.poll();

			if(visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			result+= p.weight;
			for( Vertex4 vt : list[p.edge]){
				if(!visited[vt.edge] && vt.weight < dist[vt.edge]) {
					pq.offer(vt);
					dist[vt.edge] = vt.weight;
				}
			}
			
		}
        int minCost = 0;
        for (int i = 0; i < n; i++) {
            minCost += Math.min(w[i], dist[i]);
        }
		
		System.out.println(minCost);
	}

}
