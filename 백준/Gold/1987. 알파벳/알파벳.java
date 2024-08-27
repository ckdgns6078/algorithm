import java.util.*;

public class Main {

	static int r, c;
	static char[][] map;
	static Set<Character> set = new HashSet<Character>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		map = new char[r][c];

		for (int i = 0; i < r; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
			}
		}
		set.add(map[0][0]);
		checkMove(0, 0, 1);

		System.out.println(result);

	}

	// dfs를 활용해야한다.
	private static void checkMove(int rr, int cc, int tmp) {
		result = Math.max(result, tmp);
		for (int i = 0; i < 4; i++) {
			int nr = rr + dr[i];
			int nc = cc + dc[i];

			if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
				if (!set.contains(map[nr][nc])) {
					set.add(map[nr][nc]);
					checkMove(nr, nc, tmp + 1);
					set.remove(map[nr][nc]);
				}

			}
		}
	}

}
