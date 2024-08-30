import java.util.*;

class Vertex implements Comparable<Vertex>{
	int edge;
	int weight;
	
	Vertex(int e , int w){
		this.edge = e;
		this.weight = w;
	}
	
	@Override
	public int compareTo(Vertex o) {
		
		return Integer.compare(this.weight, o.weight);
	}
	
	
	
}

public class Solution {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc= 1; tc<=T ; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			long result = 0;
			ArrayList<Vertex>[] list = new ArrayList[n+1];
			
			for(int i = 1 ; i < n+1 ; i++) {
				list[i] = new ArrayList();
			}
			
			for( int i = 0 ; i < m ; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int w = sc.nextInt();
				
				list[x].add(new Vertex(y,w));
				list[y].add(new Vertex(x,w));
			}
			
			int[] dist = new int[n+1];
			boolean[] v = new boolean[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			PriorityQueue<Vertex> pq = new PriorityQueue();
			pq.offer(new Vertex(1,0));
			dist[1]=0;
			
			while(!pq.isEmpty()) {
				Vertex p = pq.poll();
				
				
				if(v[p.edge]) {
					continue;
				}
				v[p.edge] = true;
				result+= p.weight;
				
				for( Vertex vt : list[p.edge]) {
					if(!v[vt.edge] && vt.weight < dist[vt.edge]) {
						pq.offer(vt);
						dist[vt.edge] = vt.weight;
					}
					
				}
				
				
			}
			
			
			System.out.println("#" + tc +" " + result);
			
		}
		

	}

}
