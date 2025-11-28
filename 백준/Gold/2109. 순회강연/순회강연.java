import java.util.*;

public class Main {

	public static void main(String[] args) {

		PriorityQueue<Class> pq = new PriorityQueue();
		List<Class> list = new ArrayList();

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int p = sc.nextInt();
			int d = sc.nextInt();
			list.add(new Class(d, p));
		}

		list.sort((a, b) -> Integer.compare(a.d, b.d));

		for (Class c : list) {

			pq.offer(c);
			if (pq.size() > c.d) {
				pq.poll();
			}
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			answer += pq.poll().p;
		}
		System.out.println(answer);

	}

	public static class Class implements Comparable<Class>{
		int d;
		int p;

		
		public Class(int d, int p) {
			this.d = d;
			this.p = p;
		}


		@Override
		public int compareTo(Class o) {
			 
			return Integer.compare(this.p , o.p);
		}
	}
}
