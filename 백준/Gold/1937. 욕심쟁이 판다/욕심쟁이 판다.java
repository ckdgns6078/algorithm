import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static int[][] dp;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[N][N];
		dp = new int[N][N];

		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, dfs(i, j));
			}
		}
		System.out.println(answer);
	}

	public static int dfs(int r, int c) {
		if (dp[r][c] != 0) {
			return dp[r][c];
		}

		dp[r][c] = 1;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] > map[r][c]) {
				dp[r][c] = Math.max(dp[r][c], dfs(nr,nc)+1);
			}
		}
		return dp[r][c];
	}

}
