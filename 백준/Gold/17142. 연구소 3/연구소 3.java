import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int N, M;
	static int answer = Integer.MAX_VALUE;
	static boolean[][] visited;
	static ArrayList<int[]> virusList = new ArrayList();
	static int emptyCheck = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					virusList.add(new int[] { i, j });
				}
				if (map[i][j] == 0) {
					emptyCheck++;
				}

			}
		}
		combination(0, 0, new int[M]);

		if (emptyCheck == 0) {
			System.out.println(0);
		} else {
			System.out.println(answer == Integer.MAX_VALUE ? -1 : (answer - 1));
		}

	}

	public static void bfs(int[] sel) {
		int cnt = emptyCheck;
		int result = 0;
		visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque();
		for (int i = 0; i < sel.length; i++) {
			int[] temp = virusList.get(sel[i]);
			q.offer(new int[] { temp[0], temp[1] });
			visited[temp[0]][temp[1]] = true;
		}

		while (!q.isEmpty()) {
			int size = q.size();


			result++;
			if (cnt ==0) {
				answer = Math.min(answer, result);
				return;
			}
			for (int k = 0; k < size; k++) {
				int[] p = q.poll();
				int r = p[0];
				int c = p[1];
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
						if(map[nr][nc]==0) {
							cnt--;
						}
						
					}
				}
			}
		}
		if (mapCheck()) {
			answer = Math.min(answer, result);
		}

	}

	public static boolean mapCheck() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public static void combination(int idx, int k, int[] sel) {
		if (k == sel.length) {
			bfs(sel);
			return;
		}
		if (idx == virusList.size()) {
			return;
		}

		sel[k] = idx;
		combination(idx + 1, k + 1, sel);
		combination(idx + 1, k, sel);

	}

}
