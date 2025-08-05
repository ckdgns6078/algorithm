import java.util.*;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int M;
	static int K;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
					pq.offer(new int[] { i, j, map[i][j] });
					visited[i][j] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		K = sc.nextInt();
		int temp = 0;
		while (!pq.isEmpty() && temp < K) {

			int[] p = pq.poll();
			int r = p[0];
			int c = p[1];
			int corn = p[2];
			temp++;

			sb.append((r + 1) + " " + (c + 1)).append("\n");

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
					pq.offer(new int[] { nr, nc, map[nr][nc] });
					visited[nr][nc] = true;
				}
			}
		}

		System.out.println(sb.toString());

	}

}
