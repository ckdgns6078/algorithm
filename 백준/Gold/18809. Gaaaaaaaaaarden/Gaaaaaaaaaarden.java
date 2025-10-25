import java.util.*;

public class Main {

	static int N;
	static int M;
	static int G;
	static int R;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] garden;
	static int answer = 0;
	static List<int[]> greenList = new ArrayList();
	static List<int[]> redList = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		G = sc.nextInt();
		R = sc.nextInt();

		garden = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				garden[i][j] = sc.nextInt();
			}
		}

		rec(0, 0, 0, 0);
		System.out.println(answer);
	}

	public static void rec(int r, int c, int green, int red) {
		if (G == green && red == R) {
			bfs();
			return;
		}

		if (r >= N) {
			return;
		}

		int nr = c + 1 >= M ? r + 1 : r;
		int nc = c + 1 >= M ? 0 : c + 1;

		if (garden[r][c] == 2) { // 배양이 가능한 곳일 때
			if (green < G) {
				greenList.add(new int[] { r, c });
				rec(nr, nc, green + 1, red);
				greenList.remove(greenList.size() - 1);
			}

			if (red < R) {
				redList.add(new int[] { r, c });
				rec(nr, nc, green, red + 1);
				redList.remove(redList.size() - 1);
			}
		}
		rec(nr, nc, green, red);

	}

	public static void bfs() {
		int[][][] map = new int[3][N][M];
		boolean[][][] visited = new boolean[3][N][M];
		Queue<int[]> q = new ArrayDeque();
		int result = 0;

		for (int i = 0; i < greenList.size(); i++) {
			int gr = greenList.get(i)[0];
			int gc = greenList.get(i)[1];
			map[1][gr][gc] = 0;
			visited[1][gr][gc] = true;
			q.offer(new int[] { gr, gc, 1 });
		}

		for (int i = 0; i < redList.size(); i++) {
			int rr = redList.get(i)[0];
			int rc = redList.get(i)[1];
			map[2][rr][rc] = 0;
			visited[2][rr][rc] = true;
			q.offer(new int[] { rr, rc, 2 });
		}

		int turn = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			turn++;
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int r = p[0];
				int c = p[1];
				int color = p[2];

				int diff = color == 1 ? 2 : 1;

				if(visited[0][r][c]) {
					continue;
				}
				
				if (!visited[0][r][c] && map[1][r][c] != 0 && map[2][r][c] != 0 && map[1][r][c] == map[2][r][c]) {
					visited[0][r][c] = true;
					result++;
					continue;
				}

				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
						continue;
					}
					if (visited[color][nr][nc]) {
						continue;
					}
					if (garden[nr][nc] == 0) {
						continue;
					}

					q.offer(new int[] { nr, nc, color });
					visited[color][nr][nc] = true;
					map[color][nr][nc] = turn + 1;
				}
			}
		}

		answer = Math.max(result, answer);
	}
}
