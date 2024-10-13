
import java.util.*;

class Vertex2 implements Comparable<Vertex2>{
	int edge;
	int weight;
	
	public Vertex2(int edge , int weight) {
		this.edge = edge;
		this.weight = weight;
	}
	@Override
	public int compareTo(Vertex2 o) {
		return Integer.compare(this.weight, o.weight);
	}
}
public class Main {
	static int n;
	static int m;
	static int[] dist;
	static ArrayList<Vertex2>[] list;
	static int[] parents;
	static int startPoint;
	static int endPoint;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		parents = new int[n+1];
		list = new ArrayList[n+1];
		for(int i=0; i< n+1 ; i++) {
			list[i] = new ArrayList();
		}

		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0; i < m ; i++) {
			int start =sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			list[start].add(new Vertex2(end , weight));
		}
		
		startPoint = sc.nextInt();
		endPoint = sc.nextInt();
		
		PriorityQueue<Vertex2> q = new PriorityQueue();
		q.offer(new Vertex2(startPoint , 0));
		dist[startPoint] = 0;
		
		while(!q.isEmpty()) {
			Vertex2 p = q.poll();
			
			if(p.edge == endPoint) {
				break;
			}
			
			
			
			for(Vertex2 next : list[p.edge]) {
				if(next.weight + dist[p.edge] < dist[next.edge]) {
					dist[next.edge] = next.weight + dist[p.edge];
					q.offer(new Vertex2(next.edge , dist[next.edge]));
					parents[next.edge] = p.edge;
				}
			}
			
			
		}
		
		StringBuilder sb = new StringBuilder();
		List<Integer> path = new ArrayList<>();
		
		int node = endPoint;		
		while(node !=0) {
			path.add(node);
			node = parents[node];
		}
		
		
		int sum = dist[endPoint];
		sb.append(sum);
		sb.append("\n");
		sb.append(path.size());
		sb.append("\n");
		for(int i=path.size()-1 ; i >=0 ; i--) {
			sb.append(path.get(i));
			sb.append(" ");
		}
		System.out.println(sb);
		
	}

}
