import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] wall;
	static boolean[][] visited;
	static int[][] checkList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<int[]> wallList;
	static ArrayList<int[]> pathList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		wall = new boolean[n][m];
		checkList = new int[n][m];
		visited = new boolean[n][m];
		wallList = new ArrayList();
		pathList = new ArrayList();

		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < m; j++) {
				int c = input.charAt(j) - '0';
				map[i][j] = c;
				if (c == 1) {
					wallList.add(new int[] { i, j });
					wall[i][j] = true;
				} else {
					pathList.add(new int[] { i, j });
				}
			}
		}

		int cnt = 1;
		for (int i = 0; i < pathList.size(); i++) {
			int r = pathList.get(i)[0];
			int c = pathList.get(i)[1];

			if (map[r][c] == 0) {
				bfs(r, c, cnt);
				cnt++;
			}

		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (wall[i][j]) {
					sb.append(cellCount(i, j));
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int cellCount(int r, int c) {
		Set<Integer> set = new HashSet();
		int cnt = 1;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !wall[nr][nc]) {
				if (!set.contains(checkList[nr][nc])) {
					cnt += map[nr][nc];
					set.add(checkList[nr][nc]);
				}
			}
		}
		return cnt % 10;
	}

	private static void bfs(int r, int c, int check) {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { r, c });
		ArrayList<int[]> resultList = new ArrayList();
		resultList.add(new int[] { r, c });
		int cnt = 0;
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && !wall[nr][nc]) {
					int[] node = { nr, nc };
					q.offer(node);
					resultList.add(node);
					visited[nr][nc] = true;
				}
			}
		}

		for (int i = 0; i < resultList.size(); i++) {
			map[resultList.get(i)[0]][resultList.get(i)[1]] = cnt;
			checkList[resultList.get(i)[0]][resultList.get(i)[1]] = check;
		}

	}

}
