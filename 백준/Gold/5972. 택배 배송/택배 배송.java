import java.util.*;

public class Main {

	static int n;
	static int m;
	static int[] dist;
	static ArrayList<Node>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		dist = new int[n+1];
		list =new ArrayList[n+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i =1 ; i<n+1;i++) {
			list[i] = new ArrayList<Node>();
		}
		
		
		for(int i=0;i<m;i++) {
			int sn = sc.nextInt();
			int en = sc.nextInt();
			int di = sc.nextInt();
			
			list[sn].add(new Node(en , di));
			list[en].add(new Node(sn , di));
			
		}
		dijk(1);
		
		System.out.println(dist[n]);
		
		
	}
	
	public static void dijk(int start) {
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.offer(new Node(start ,0));
		
		dist[start] =0;
		
		while(!pq.isEmpty()) {
			
			Node p = pq.poll();
			int idx = p.idx;
			int dis = p.dis;
			
			if(dist[idx] < dis) {
				continue;
			}
			
			for( Node next : list[idx]) {
				int cost = dis + next.dis;
				
				if(dist[next.idx] > cost) {
					dist[next.idx] = cost;
					pq.offer(new Node(next.idx , cost));
				}
				
			}
			
		}
	}
	
	
	
	static class Node implements Comparable<Node>{
		int idx;
		int dis;
		
		public Node(int idx , int dis) {
			this.idx = idx;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}
}
