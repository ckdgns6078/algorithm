import java.util.*;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int R, C;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] opposite = { 1, 0, 3, 2 };

	static boolean[] s1 = { true, true, false, false };
	static boolean[] s2 = { false, false, true, true };
	static boolean[] s3 = { true, true, true, true };
	static boolean[] s4 = { false, true, false, true };
	static boolean[] s5 = { true, false, false, true };
	static boolean[] s6 = { true, false, true, false };
	static boolean[] s7 = { false, true, true, false };

	static int resultR, resultC;
	static char resultBlock;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		visited = new boolean[R][C];
		int startR = 0, startC = 0;

		for (int i = 0; i < R; i++) {
			String s = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'M') {
					startR = i;
					startC = j;
				}
			}
		}

		bfs(startR, startC);
		System.out.println(resultR + " " + resultC + " " + resultBlock);
	}

	public static void bfs(int startR, int startC) {
		Queue<Node> q = new ArrayDeque<>();

		for (int d = 0; d < 4; d++) {
			int nr = startR + dr[d];
			int nc = startC + dc[d];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			char next = map[nr][nc];
			if (next == '.' || next == 'M')
				continue;
			boolean[] nextShape = nextDir(nr, nc);
			if (nextShape[opposite[d]]) {
				visited[nr][nc] = true;
				q.offer(new Node(nr, nc, d));
				break;
			}
		}

		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			boolean[] curShape = nextDir(r, c);

			for (int d = 0; d < 4; d++) {
				if (!curShape[d])
					continue;
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				char next = map[nr][nc];

				if (next == '.') {
					findMissingBlock(nr, nc);
					return;
				}

				boolean[] nextShape = nextDir(nr, nc);
				if (!visited[nr][nc] && nextShape[opposite[d]]) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, d));
				}
			}
		}
	}

	public static boolean[] nextDir(int r, int c) {
		if (map[r][c] == '|')
			return s1;
		else if (map[r][c] == '-')
			return s2;
		else if (map[r][c] == '+')
			return s3;
		else if (map[r][c] == '1')
			return s4;
		else if (map[r][c] == '2')
			return s5;
		else if (map[r][c] == '3')
			return s6;
		else if (map[r][c] == '4')
			return s7;
		return new boolean[4];
	}

	public static void findMissingBlock(int r, int c) {
		boolean[] connect = new boolean[4];
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			char near = map[nr][nc];
			if (near == '.' || near == 'M' || near == 'Z')
				continue;
			boolean[] nearShape = nextDir(nr, nc);
			if (nearShape[opposite[d]])
				connect[d] = true;
		}
		resultBlock = getBlock(connect);
		resultR = r + 1;
		resultC = c + 1;
	}

	public static char getBlock(boolean[] c) {
		if (c[0] && c[1] && c[2] && c[3])
			return '+';
		if (c[0] && c[1])
			return '|';
		if (c[2] && c[3])
			return '-';
		if (c[1] && c[3])
			return '1';
		if (c[0] && c[3])
			return '2';
		if (c[0] && c[2])
			return '3';
		if (c[1] && c[2])
			return '4';
		return '?';
	}

	static class Node {
		int r, c, dir;

		Node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
