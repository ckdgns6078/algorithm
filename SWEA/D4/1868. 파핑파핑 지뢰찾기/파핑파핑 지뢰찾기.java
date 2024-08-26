import java.util.*;

public class Solution {

	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, 1, -1, -1, 1, 1, -1 };
	static char[][] mine;
	static int n;
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			n = sc.nextInt();
			mine = new char[n][n];
			result=0;
			for (int i = 0; i < n; i++) {
				String input = sc.next();
				for (int j = 0; j < input.length(); j++) {
					mine[i][j] = input.charAt(j);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					boolean check = true;
					for (int k = 0; k < 8; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
							if (mine[nr][nc] == '*') {
								check = false;
								break;
							}
						}
					}
					if (check && mine[i][j] == '.') {
//						System.out.println("( " + i + " , "+j + " ) 에서 실행");
						checkMine(i, j);
						result++;
					}

				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (mine[i][j] == '.') {
						result++;
					}
				}
			}

			System.out.println("#" + tc + " " + result);

		}
	}

	private static void checkMine(int r, int c) {

		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { r, c });
		mine[r][c] = '0';

		while (!q.isEmpty()) {
			int[] p = q.poll();
			boolean check = false;

			for (int i = 0; i < 8; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
					if (mine[nr][nc] == '*') {
						check = true;
						break;
					}
				}
			}
			if (!check) {
				for (int i = 0; i < 8; i++) {
					int nr = p[0] + dr[i];
					int nc = p[1] + dc[i];

					if (nr >= 0 && nr < n && nc >= 0 && nc < n && mine[nr][nc] == '.') {
						q.offer(new int[] { nr, nc });
						mine[nr][nc] = '0';
					}

				}
			}

		}

	}
}
