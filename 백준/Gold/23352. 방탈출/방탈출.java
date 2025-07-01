import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int len = Integer.MIN_VALUE;
	static int answer = Integer.MIN_VALUE;
	static ArrayList<int[]> pointList = new ArrayList();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0) {
					pointList.add(new int[] { i, j });
				}
			}
		}

		if (pointList.size() == 0) {
			System.out.println(0);
			System.exit(0);
		}
		for (int i = 0; i < pointList.size(); i++) {
			int r = pointList.get(i)[0];
			int c = pointList.get(i)[1];
			bfs(r, c);
		}
		System.out.println(answer);
	}

	public static void bfs(int r, int c) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque();
		visited[r][c] = true;
		q.offer(new int[] { r, c, 1 });

		while (!q.isEmpty()) {
			int[] p = q.poll();

			
			if(len == p[2]) {
				answer = Math.max(answer, map[r][c] + map[p[0]][p[1]]);
			}else if( len < p[2]) {
				len = p[2];
				answer = map[r][c] + map[p[0]][p[1]];
			}

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] > 0 && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc, p[2] + 1 });
					visited[nr][nc] = true;
				}
			}

		}
	}
}
