import java.util.*;

public class Main {

	static int N, M, K;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		List<Queue<Node>> lines = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			lines.add(new LinkedList<>());
		}

		for (int i = 1; i <= N; i++) {
			int D = sc.nextInt();
			int H = sc.nextInt();
			boolean check = (i == K + 1);
			int idx = (i - 1) % M;
			lines.get(idx).offer(new Node(i, D, H, check, idx));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			if (!lines.get(i).isEmpty()) {
				pq.offer(lines.get(i).poll());
			}
		}

		while (true) {
			Node n = pq.poll();
			if (n.check)
				break;
			answer++;

			if (!lines.get(n.line).isEmpty()) {
				pq.offer(lines.get(n.line).poll());
			}
		}

		System.out.println(answer);
	}

	static class Node implements Comparable<Node> {
		int D, H, num, line;
		boolean check;

		public Node(int num, int D, int H, boolean check, int line) {
			this.D = D;
			this.H = H;
			this.num = num;
			this.check = check;
			this.line = line;
		}

		public int compareTo(Node o) {
			if (this.D != o.D)
				return Integer.compare(o.D, this.D); // 근무일수 내림차순
			if (this.H != o.H)
				return Integer.compare(o.H, this.H); // 급한 정도 내림차순
			return Integer.compare(this.line, o.line); // 줄 번호 오름차순
		}
	}
}
