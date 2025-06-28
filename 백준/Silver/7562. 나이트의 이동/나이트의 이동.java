import java.util.*;

public class Main {

	static int N;
	static boolean[][] visited;
	static int answer;
	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();

		for (int tc = 0; tc < testCase; tc++) {
			N = sc.nextInt();
			visited = new boolean[N][N];

			int startR = sc.nextInt();
			int startC = sc.nextInt();
			int endR = sc.nextInt();
			int endC = sc.nextInt();
			answer = 0;
			bfs(startR, startC, endR, endC);
			System.out.println(answer);
		}
	}

	public static void bfs(int sr, int sc, int er, int ec) {
		Queue<int[]> q = new ArrayDeque();
		visited[sr][sc] = true;
		q.offer(new int[] { sr, sc, 0 });

		while (!q.isEmpty()) {

			int[] p = q.poll();

			if (p[0] == er && p[1] == ec) {
				answer = p[2];
				return;
			}

			for (int i = 0; i < 8; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc, p[2] + 1 });
					visited[nr][nc] = true;
				}

			}

		}

	}

}
