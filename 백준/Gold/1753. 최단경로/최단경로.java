import java.util.*;

class Vertex implements Comparable<Vertex>{
	int edge;
	int weight;
	
	Vertex(int e , int w){
		this.edge = e;
		this.weight = w;
	}
	
	public int compareTo(Vertex o) {
		return Integer.compare(this.weight, o.weight);
	}
	
	
	
}

public class Main {
	static int v;//정점의 개수
	static int e;//간선의 개수
	static ArrayList<Vertex>[] list;
	static int k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		v = sc.nextInt();
		e = sc.nextInt();
		k = sc.nextInt();
		list = new ArrayList[v+1];
		
		for(int i = 1 ; i < v+1 ; i++) {
			list[i]=new ArrayList();
		}
		for(int i = 0 ; i < e ; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list[a].add(new Vertex(b,c));
		}
		
		int[] dist = new int[v+1];
		boolean[] visited = new boolean[v+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Vertex> q = new PriorityQueue();
		q.offer(new Vertex(k,0));
		dist[k] = 0;
		
		while(!q.isEmpty()) {
			Vertex p = q.poll();
			
			if(visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			for(Vertex next : list[p.edge]) {
				if(!visited[next.edge] && next.weight + dist[p.edge]<dist[next.edge] ) {					
					dist[next.edge] = next.weight + dist[p.edge];
					q.add(new Vertex(next.edge , dist[next.edge]));
				}
			} 
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i < dist.length ; i++) {
			sb.append(dist[i]==Integer.MAX_VALUE ? "INF" : dist[i]);
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}
