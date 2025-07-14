import java.util.*;

public class Main {

	static int start;
	static int end;
	static int answer = Integer.MAX_VALUE;
	static int cnt = 0;
	static Map<Integer, Integer> visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		start = sc.nextInt();
		end = sc.nextInt();
		visited = new HashMap();

		bfs(start);
		System.out.println(answer);
		System.out.println(cnt);
	}

	public static void bfs(int first) {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { first, 0 });
		visited.put(first, 0);

		while (!q.isEmpty()) {

			int[] p = q.poll();

			int now = p[0];
			int turn = p[1];

			if (now == end) {
				if (turn < answer) {
					answer = turn;
					cnt = 1;
				} else if (turn == answer) {
					cnt++;
				}
			}

			int[] arr = { now - 1, now + 1, now * 2 };
			for (int next : arr) {
				if (next < 0 || next > 100000) {
					continue;
				}

				if (!visited.containsKey(next) || visited.get(next) == turn + 1) {
					visited.put(next, turn + 1);
					q.offer(new int[] { next, turn + 1 });
				}
			}

		}

	}

}
