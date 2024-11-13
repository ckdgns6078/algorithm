import java.util.*;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}

		System.out.println(answer);
	}

	private static void bfs(int r, int c) {
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> q = new ArrayDeque();
		visited[r][c] = true;
		q.offer(new int[] { r, c, 0 });

		while (!q.isEmpty()) {
			int[] p = q.poll();

			answer = Math.max(answer, p[2]);
			
			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && map[nr][nc] == 'L') {
					q.offer(new int[] { nr, nc, p[2] + 1 });
					visited[nr][nc] = true;

				}

			}

		}

	}
}
