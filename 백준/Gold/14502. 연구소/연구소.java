import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static ArrayList<int[]> list = new ArrayList();
	static ArrayList<int[]> virus = new ArrayList();
	static int result = Integer.MIN_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int safeArea = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					virus.add(new int[] { i, j });

				} else if (map[i][j] == 0) {
					list.add(new int[] { i, j });
					safeArea++;
				}
			}
		}

		safeArea += virus.size() - 3;
		combination(0, 0, new int[3][]);
		System.out.println(result);

	}

	private static void combination(int idx, int k, int[][] sel) {
		if (sel.length == k) {
			// bfs 호출
			bfs(sel);
			return;
		}
		if (idx == list.size()) {
			return;
		}
		sel[k] = list.get(idx);
		combination(idx + 1, k + 1, sel);
		combination(idx + 1, k, sel);
	}

	private static void bfs(int[][] wall) {
		boolean[][] v = new boolean[n][m];
		Queue<int[]> q = new ArrayDeque();
		int virusCount = virus.size();

		// 벽 생성
		for (int i = 0; i < wall.length; i++) {
			map[wall[i][0]][wall[i][1]] = 1;
		}

		for (int i = 0; i < virus.size(); i++) {
			int rr = virus.get(i)[0];
			int cc = virus.get(i)[1];
			q.offer(new int[] { rr, cc });
			v[rr][cc] = true;
		}

		while (!q.isEmpty()) {
			int[] p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !v[nr][nc] && map[nr][nc] == 0) {
					q.offer(new int[] { nr, nc });
					v[nr][nc] = true;
					virusCount++;
				}
			}
		}

		result = Math.max(result, safeArea - virusCount);

		for (int i = 0; i < wall.length; i++) {
			map[wall[i][0]][wall[i][1]] = 0;
		}

	}

}
