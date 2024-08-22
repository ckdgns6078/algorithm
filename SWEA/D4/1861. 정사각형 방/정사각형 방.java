import java.util.*;

public class Solution {
	static int n;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int moveMin;
	static int moveCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			moveMin = Integer.MAX_VALUE;
			moveCount = Integer.MIN_VALUE;
			n = sc.nextInt();
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 모든 경우의 수를 다 검사해야한다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bfs(i, j);
				}
			}
			//이동가능한 횟수 + 시작점
			System.out.println("#" + tc + " " + moveMin +" " + moveCount);
			
		}

	}

	private static void bfs(int r, int c) {
		int cycle = 0;
		// 마지막으로 이동한 숫자 중 가장 작은숫자 여야한다.
		// 마지막 인덱스가 값이된다.
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int[] p = q.poll();

				for (int i = 0; i < 4; i++) {
					int nr = p[0] + dr[i];
					int nc = p[1] + dc[i];
					int cnt = map[p[0]][p[1]];

					if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == (cnt + 1)) {
						q.offer(new int[] { nr, nc });
					}

				}

			}
			cycle++;
		}
		
		if(cycle > moveCount) {
			moveCount = cycle;
			moveMin = map[r][c];
			
		}else if(cycle == moveCount) {
			moveMin = Math.min(moveMin, map[r][c]);
		}
		
		
		
	}

}
