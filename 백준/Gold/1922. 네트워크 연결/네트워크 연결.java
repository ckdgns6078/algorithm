
import java.util.*;


class Ver implements Comparable<Ver>{
	int edge;
	int weight;
	
	Ver(int e , int w){
		this.edge = e;
		this.weight = w;
	}

	@Override
	public int compareTo(Ver o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.weight, o.weight);
	}
	
}


public class Main {
	static int n , m;
	static ArrayList<Ver>[] list;
	static int[] dist;
	static boolean[]v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();

		list = new ArrayList[n+1];
		for( int i = 1 ; i < n+1 ; i++) {
			list[i] = new ArrayList();
		}
		
		dist = new int[n+1];
		Arrays.fill(dist , Integer.MAX_VALUE);
		
		v= new boolean[n+1];
		
		for( int i = 0 ; i < m ; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int weight = sc.nextInt();
			
			list[r].add(new Ver(c,weight));
			list[c].add(new Ver(r,weight));
			
		}
		
		int result = 0;
		PriorityQueue<Ver> pq = new PriorityQueue<Ver>();
		pq.add(new Ver(1,0));
		dist[1]=0;
	
		while(!pq.isEmpty()) {
			Ver p = pq.poll();
			
			if(v[p.edge]) {
				continue;
			}
			v[p.edge] = true;
			result+=p.weight;
			for(Ver next : list[p.edge]) {
				if(!v[next.edge] && next.weight < dist[next.edge]) {
					pq.offer(next);
					dist[next.edge] = next.weight;
				}
			}
			
			
		}
		System.out.println(result);
		
		
	}

}
