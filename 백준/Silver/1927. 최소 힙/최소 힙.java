import java.util.*;

public class Main {

	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue();
		for (int i = 0; i < N; i++) {
			int cnt = sc.nextInt();
			if(cnt ==0) {
				sb.append(pq.size() == 0 ? 0 : pq.poll()).append("\n");
				continue;
			}
			pq.offer(cnt);
		}
		
		System.out.println(sb.toString());
		
	}
}
