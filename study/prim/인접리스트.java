package graph;

import java.util.*;

class Vertex implements Comparable<Vertex> {
	int e, w;

	Vertex(int e, int w) {
		this.e = e;
		this.w = w;
	}

	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.w, o.w);
	}
}

public class Prim인접리스트 {
	static int V, E;
	static ArrayList<Vertex>[] adj;
	static int[] dist; // 거리배열
	static boolean[] v; // 정점배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		// 인접리스트
		adj = new ArrayList[V];

		// 리스트 초기화 시키기
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList();
		}

		// 거리배열
		dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열 제일 큰값으로 초기화

		// 정점배열
		v = new boolean[V];

		// 향상된 프라이오리티 큐를 사용하기 위해서 최소 기준정점 찾기를 대신할 PriorityQueue를 하나 선언합니다.
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();	// 내부적으로 힙정렬을 사용해서 시간복잡도는 log(n)이 된다

		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			adj[a].add(new Vertex(b, c));
			adj[b].add(new Vertex(a, c));
		}

		// 향상된 프림 ( 인접리스트 )
		// 임의의 기준정점 0을 q에 넣는다.
		q.add(new Vertex(0, 0));
		dist[0] = 0;

		while (!q.isEmpty()) {
			// 기준 정점
			Vertex p = q.poll();

			// 기준정점을 한 적이 있는 친구는 검사하지마라.
			if (v[p.e])
				continue;

			// 기준정점 체크
			v[p.e] = true;

			// p.e와 연결된 정점을 찾아서 q에 넣는다.
			for (Vertex next : adj[p.e]) {
				if (!v[next.e] && next.w < dist[next.e]) { // 방문을 하지않고 새롭게 나온 값이 기존의 값보다 작다면
					q.add(next);
					dist[next.e] = next.w;
				}
			}

		}
		System.out.println(Arrays.toString(dist));
	}

}
