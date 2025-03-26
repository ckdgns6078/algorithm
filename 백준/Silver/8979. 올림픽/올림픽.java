import java.util.*;

public class Main {

	static int N;
	static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		PriorityQueue<Raiting> pq = new PriorityQueue();

		for (int i = 0; i < N; i++) {
			int idx = sc.nextInt();
			int g = sc.nextInt();
			int s = sc.nextInt();
			int b = sc.nextInt();
			pq.offer(new Raiting(idx, g, s, b));
		}

		Raiting r = pq.poll();
		int cnt = 1;
		int temp = 1;
		int bg = r.g;
		int bs = r.s;
		int bb = r.b;
		int size = pq.size();

		if (r.idx == M) {
			System.out.println(cnt);
		} else {
			for (int i = 0; i < size; i++) {
				r = pq.poll();
				temp++;
				if (bg != r.g || bs != r.s || bb != r.b) {
					cnt = temp;
					bg = r.g;
					bs = r.s;
					bb = r.b;
				}
				/**
				 * 값이 4개와 같으면 같은 cnt 다르면 temp로 변경하고 cnt를 temp로 변경 bg bs bb 를 수정한다.
				 */
				if (r.idx == M) {
					break;
				}
			}
			System.out.println(cnt);
		}

	}

	static class Raiting implements Comparable<Raiting> {
		int idx;
		int g;
		int s;
		int b;

		Raiting(int idx, int g, int s, int b) {
			this.idx = idx;
			this.g = g;
			this.s = s;
			this.b = b;
		}

		@Override
		public int compareTo(Raiting o) {
			if (this.g != o.g) {
				return Integer.compare(o.g , this.g);
			}
			if (this.s != o.s) {
				return Integer.compare(o.s , this.s);
			}
			return Integer.compare(o.b , this.b);

		}

	}

}
