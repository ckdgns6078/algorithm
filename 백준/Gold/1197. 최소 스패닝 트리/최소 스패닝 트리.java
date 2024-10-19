import java.util.*;

class  Spanning implements Comparable<Spanning>{
	int edge;
	int weight;
	
	public Spanning(int edge , int weight) {
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public int compareTo(Spanning o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.weight, o.weight);
	}	
}
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();	//정점의 개수
		int e = sc.nextInt();	//간선의 개수
		
		ArrayList<Spanning>[] list = new ArrayList[v+1];
		for(int i=0; i < v+1 ; i++) {
			list[i] = new ArrayList();
		}
		int[] dist = new int[v+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[v+1];
		
		for(int i=0; i < e ; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			list[start].add(new Spanning(end , weight));
			list[end].add(new Spanning(start , weight));
			
		}
		
		PriorityQueue<Spanning> q = new PriorityQueue();
		q.offer(new Spanning(1 ,0));
		dist[1]=0;
		long sum =0;
		while(!q.isEmpty()) {
			Spanning p = q.poll();
			
			if(visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			sum+=p.weight;
			
			for(Spanning next : list[p.edge]) {
				if(!visited[next.edge] && next.weight < dist[next.edge]) {
					dist[next.edge] = next.weight;
					q.offer(next);
				}
				
			}
			
		}
		System.out.println(sum);
		
	}

}
