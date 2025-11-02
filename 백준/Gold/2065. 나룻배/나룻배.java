
import java.util.*;

public class Main {

	static int M;
	static int t;
	static int N;
	static PriorityQueue<Node> left = new PriorityQueue<>();
	static PriorityQueue<Node> right = new PriorityQueue<>();
	static Map<Integer, Integer> resultMap = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		M = sc.nextInt();
		t = sc.nextInt();
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int time = sc.nextInt();
			String type = sc.next();
			if (type.equals("left")) {
				left.add(new Node(time, i + 1));
			} else {
				right.add(new Node(time, i + 1));
			}
			resultMap.put(i + 1, 0);
		}

		int done = 0;
		int nowTime = 0;
		int dir = 0;

		while (done < N) {
			boolean curHas = check(nowTime, dir);
			boolean otherHas = check(nowTime, dir ^ 1);

			if (curHas) {
				int boarded = takeOn(nowTime, dir);
				nowTime += t;
				done += boarded;
				dir ^= 1;
			} else {
				if (otherHas) {
					nowTime += t;
					dir ^= 1;
				} else {
					int nextLeft = left.isEmpty() ? Integer.MAX_VALUE : left.peek().time;
					int nextRight = right.isEmpty() ? Integer.MAX_VALUE : right.peek().time;
					int nextTime = Math.min(nextLeft, nextRight);
					if (nextTime > nowTime)
						nowTime = nextTime;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(resultMap.get(i)).append('\n');
		}
		System.out.print(sb);
	}

	public static boolean check(int nowTime, int dir) {
		PriorityQueue<Node> q = (dir == 0) ? left : right;
		if (q.isEmpty())
			return false;
		return q.peek().time <= nowTime;
	}

	public static int takeOn(int nowTime, int dir) {
		PriorityQueue<Node> q = (dir == 0) ? left : right;
		int cnt = 0;
		int arriveTime = nowTime + t;
		while (cnt < M && !q.isEmpty() && q.peek().time <= nowTime) {
			Node n = q.poll();
			resultMap.put(n.idx, arriveTime);
			cnt++;
		}
		return cnt;
	}

	static class Node implements Comparable<Node> {
		int time;
		int idx;

		public Node(int time, int idx) {
			this.time = time;
			this.idx = idx;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
}
