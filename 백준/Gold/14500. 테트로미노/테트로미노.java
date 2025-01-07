import java.util.Scanner;

public class Main {

	static int[][] map;
	static int answer = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int r, c;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		map = new int[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int pr, int pc, int count, int sum) {
		if (count == 4) {
			answer = Math.max(sum, answer);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = pr + dr[i];
			int nc = pc + dc[i];

			if (nr >= 0 && nr < r && nc >= 0 && nc < c && !visited[nr][nc]) {
				
				if(count ==2) {
					visited[nr][nc] = true;
					dfs(pr , pc , count+1 , sum+map[nr][nc]);
					visited[nr][nc] = false;
				}
				
				visited[nr][nc] = true;
				dfs(nr, nc, count + 1, sum + map[nr][nc]);
				visited[nr][nc] = false;
			}
		}

	}

}
