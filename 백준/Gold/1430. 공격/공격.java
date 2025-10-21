import java.util.*;

public class Main {

	static List<Integer>[] list;
	static int N;
	static int R;
	static int D;
	static double[] enemy;
	static boolean[] visited;

	static double answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		R = sc.nextInt();
		D = sc.nextInt();

		double[][] top = new double[N + 1][2];
		visited = new boolean[N + 1];

		list = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList();
		}

		top[0][0] = sc.nextDouble();
		top[0][1] = sc.nextDouble();

		for (int i = 1; i < N + 1; i++) {
			top[i][0] = sc.nextDouble();
			top[i][1] = sc.nextDouble();
		}

		for (int i = 0; i < N + 1; i++) {
			double x1 = top[i][0];
			double y1 = top[i][1];
			for (int j = 0; j < N + 1; j++) {
				if (i == j)
					continue;

				double x2 = top[j][0];
				double y2 = top[j][1];

				double dist = getDistance(x1, y1, x2, y2);
				if (dist <= R) {
					list[i].add(j);
					list[j].add(i);
				}
			}
		}

		bfs(0);
		System.out.println(answer);

	}

	public static void bfs(int start) {

		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { start, 0 });
		visited[start] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int now = p[0];
			int turn = p[1];

			for (int next : list[now]) {
				if (!visited[next]) {
					q.offer(new int[] { next, turn + 1 });
					visited[next] = true;
						
					//값 더하기
					double add = D;
					if (turn != 0) {
						for (int i = 0; i < turn; i++) {
							add /= 2;
						}
					}
					answer += add;

				}
			}
		}
	}

	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
