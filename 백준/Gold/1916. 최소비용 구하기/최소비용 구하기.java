import java.util.*;

class V implements Comparable<V>{
	int e;
	int w;
	
	V(int e , int w){
		this.e = e;
		this.w = w;
	}
	
	@Override
	public int compareTo(V o) {
		return Integer.compare(this.w , o.w);
	}


	
}


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 * 
		 * n개의 도시
		 * m개의 도착 버스
		 * a -> b 가는데 비용 최소화
		 * a -> b 가는 비용의 최소화 시켜라
		 * m번 반복 후 시작과 도착점을 나온다.
		 * 
		 * 
		 */
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<V>[] list = new ArrayList[n+1];
		boolean[] v = new boolean[n+1];
		for(int i = 1 ; i < list.length;i++) {
			list[i] = new ArrayList();
		}
		
		for(int i = 0 ; i < m ; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			list[start].add(new V(end ,weight));
		}
		int startEdge = sc.nextInt();
		int endEdge = sc.nextInt();
		
		PriorityQueue<V> q = new PriorityQueue();
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.offer(new V(startEdge , 0));
		dist[startEdge]= 0;
		
		while(!q.isEmpty()) {
			V p = q.poll();
			if(v[p.e]) {
				continue;
			}
			v[p.e] = true;
			
			for( V next : list[p.e]) {
				if(next.w + dist[p.e] < dist[next.e]) {
					dist[next.e] = next.w + dist[p.e];
					q.add(new V(next.e , dist[next.e]));
				}
			}
			
		}
		System.out.println(dist[endEdge]);
		
	}

}
	
