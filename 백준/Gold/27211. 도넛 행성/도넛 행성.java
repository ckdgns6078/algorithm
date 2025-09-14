
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited= new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int input = sc.nextInt();
				if (input == 1) {
					map[i][j] = -1;
				} else {
					map[i][j] = input;
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					cnt++;
					bfs(i, j , cnt);
				}
			}
		}
		
		System.out.println(cnt);

	}

	public static void bfs(int r, int c , int cnt) {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		map[r][c] = cnt;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int pr = p[0];
			int pc = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = pr + dr[i];
				int nc = pc + dc[i];

				nr = nrCheck(nr);
				nc = ncCheck(nc);

				if (!visited[nr][nc] && map[nr][nc] != -1) {
					q.offer(new int[] { nr, nc });
					map[nr][nc] = cnt;
					visited[nr][nc] = true;
				}
			}
		}
	}

	public static int nrCheck(int nr) {

		if (nr < 0) {
			return N - 1;
		} else if (nr >= N) {
			return 0;
		}
		return nr;
	}

	public static int ncCheck(int nc) {
		if (nc < 0) {
			return M - 1;
		} else if (nc >= M) {
			return 0;
		}
		return nc;
	}
}
