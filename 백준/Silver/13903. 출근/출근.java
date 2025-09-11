
import java.util.*;

public class Main {

	static int R, C;
	static int[][] map;
	static int N;
	static int[][] dir;
	static int answer = Integer.MAX_VALUE;
	static int[][] visited;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		map = new int[R][C];
		visited = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		N = sc.nextInt();
		dir = new int[N][2];
		for (int i = 0; i < N; i++) {
			dir[i][0] = sc.nextInt();
			dir[i][1] = sc.nextInt();
		}

		bfs();
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque();

		for (int i = 0; i < C; i++) {
			if (map[0][i] == 1) {
				q.offer(new int[] { 0, i, 0 });
			}
		}

		while (!q.isEmpty()) {

			int[] p = q.poll();

			int r = p[0];
			int c = p[1];
			int pay = p[2];

			for (int i = 0; i < N; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				int newPay = pay+1;
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 1 && newPay < visited[nr][nc]) {
					if (nr == R - 1) {
						answer = Math.min(answer, newPay);
					} else {
						q.offer(new int[] { nr, nc, newPay });
						visited[nr][nc] = newPay;
					}
				}
			}

		}

	}

}
