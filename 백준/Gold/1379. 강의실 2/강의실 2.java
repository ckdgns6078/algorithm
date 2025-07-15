import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		PriorityQueue<Class> pq = new PriorityQueue();
		PriorityQueue<EndClass> endPq = new PriorityQueue();
		
		int[] emptyClass = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			int start = sc.nextInt();
			int end = sc.nextInt();
			pq.offer(new Class(num, start, end));
		}

		int answer = 0;

		
		while(!pq.isEmpty()) {
		
			Class now = pq.poll();
			
			
			if(!endPq.isEmpty() && endPq.peek().end <= now.start) {
				EndClass e = endPq.poll();
				emptyClass[now.num] = e.classNo;
				endPq.offer(new EndClass(now.end , now.num , e.classNo));
			}else {
				answer++;
				emptyClass[now.num] = answer;
				endPq.offer(new EndClass(now.end , now.num , answer));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(answer).append("\n");
		for(int i=1;i<N+1;i++) {
			sb.append(emptyClass[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static class EndClass implements Comparable<EndClass> {
		int end;
		int num;
		int classNo;
		EndClass(int end, int num , int classNo) {
			this.end = end;
			this.num = num;
			this.classNo = classNo;
		}

		@Override
		public int compareTo(EndClass o) {
			return Integer.compare(this.end, o.end);
		}
	}

	static class Class implements Comparable<Class> {
		int start;
		int end;
		int num;

		Class(int num, int start, int end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Class o) {
			if (this.start != o.start) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(o.end, this.end);
		}

	}
}
