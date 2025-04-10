import java.util.*;

public class Main {
	static int N, M;

	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int[][] result;
	static int startR, startC;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new boolean[N][M];
		result = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] == 2) {
					startR = i;
					startC = j;
				}
			}
		}

		bfs(startR, startC);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] ==0) {
					sb.append(0).append(" ");
					continue;
				}else if(map[i][j] ==1 && !visited[i][j]) {
					sb.append(-1).append(" ");
					continue;
				}
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	public static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { sr, sc, 0 });
		visited[sr][sc] = true;
		result[sr][sc] = 0;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int r = p[0];
			int c = p[1];
			int cnt = p[2];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc, cnt + 1 });
					visited[nr][nc] = true;
					result[nr][nc] = cnt + 1;
				}
			}
		}

	}
}
