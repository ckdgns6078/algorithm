import java.util.*;

class Point implements Comparable<Point> {

	int r;
	int c;
	int w;

	Point(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}

	@Override
	public int compareTo(Point o) {

		return Integer.compare(this.w, o.w);
	}
}

public class Main {
	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][] dist;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int cnt = 1;

		while (true) {
			N = sc.nextInt();
			if (N == 0) {
				break;
			}

			map = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			dist[0][0] = map[0][0];
			dikstra();

			sb.append("Problem ").append(cnt).append(": ").append(dist[N - 1][N - 1]).append("\n");

			cnt++;

		}

		System.out.println(sb);

	}

	public static void dikstra() {
		PriorityQueue<Point> pq = new PriorityQueue();

		pq.offer(new Point(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Point p = pq.poll();

			int r = p.r;
			int c = p.c;
			int w = p.w;

			if (dist[r][c] < w) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					int nd = dist[r][c] + map[nr][nc];

					if (nd < dist[nr][nc]) {
						dist[nr][nc] = nd;
						pq.offer(new Point(nr, nc, nd));
					}
				}

			}

		}

	}
}
