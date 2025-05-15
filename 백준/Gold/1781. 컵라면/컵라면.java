import java.util.*;

public class Main {
    
	static int n;
	static PriorityQueue<int[]> pq;
	static PriorityQueue<Integer> ramenQueue = new PriorityQueue();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		pq = new PriorityQueue<int[]>((o1, o2) -> {
			if(o1[0] != o2[0]) {
				return Integer.compare(o1[0], o2[0]);
			}
			return Integer.compare(o2[1], o1[1]);
		});

		for (int i = 0; i < n; i++) {
			int deadLine = sc.nextInt();
			int cup = sc.nextInt();
			pq.offer(new int[] { deadLine, cup });
		}

		while (!pq.isEmpty()) {
			int[] p = pq.poll();
			int deadLine = p[0];
			int ramen = p[1];

			if (ramenQueue.size() < deadLine) {
				ramenQueue.offer(ramen);
			} else {
				if (!ramenQueue.isEmpty()) {
					int smallRamen = ramenQueue.peek();
					if (smallRamen < ramen) {
						ramenQueue.poll();
						ramenQueue.offer(ramen);
					}
				}
			}

		}
		
		int answer =0;
		while(!ramenQueue.isEmpty()) {
			answer += ramenQueue.poll();
		}

		System.out.println(answer);
	}
}
