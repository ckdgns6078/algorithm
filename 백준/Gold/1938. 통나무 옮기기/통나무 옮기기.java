import java.util.*;

class Node {
	int r, c, dir, cnt;

	public Node(int r, int c, int dir, int cnt) {
		this.r = r;
		this.c = c;
		this.dir = dir;
		this.cnt = cnt;
	}
}

public class Main {
	static int N;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		List<int[]> bList = new ArrayList<>();
		List<int[]> eList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'B')
					bList.add(new int[] { i, j });
				else if (map[i][j] == 'E')
					eList.add(new int[] { i, j });
			}
		}

		Node start = getNode(bList, 0);
		Node end = getNode(eList, 0);

		visited = new boolean[N][N][2];
		bfs(start, end);
	}

	static Node getNode(List<int[]> list, int cnt) {
		int r = list.get(1)[0];
		int c = list.get(1)[1];
		int dir = (list.get(0)[0] == list.get(1)[0]) ? 0 : 1;
		return new Node(r, c, dir, cnt);
	}

	static void bfs(Node start, Node end) {
		Queue<Node> q = new LinkedList<>();
		q.offer(start);
		visited[start.r][start.c][start.dir] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.r == end.r && cur.c == end.c && cur.dir == end.dir) {
				System.out.println(cur.cnt);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (canMove(nr, nc, cur.dir)) {
					if (!visited[nr][nc][cur.dir]) {
						visited[nr][nc][cur.dir] = true;
						q.offer(new Node(nr, nc, cur.dir, cur.cnt + 1));
					}
				}
			}

			if (canRotate(cur.r, cur.c)) {
				int nDir = (cur.dir == 0) ? 1 : 0;
				if (!visited[cur.r][cur.c][nDir]) {
					visited[cur.r][cur.c][nDir] = true;
					q.offer(new Node(cur.r, cur.c, nDir, cur.cnt + 1));
				}
			}
		}

		System.out.println(0);
	}

	static boolean canMove(int r, int c, int dir) {
		if (dir == 0) {
			if (c - 1 < 0 || c + 1 >= N || r < 0 || r >= N)
				return false;
			if (map[r][c - 1] == '1' || map[r][c] == '1' || map[r][c + 1] == '1')
				return false;
		} else {
			if (r - 1 < 0 || r + 1 >= N || c < 0 || c >= N)
				return false;
			if (map[r - 1][c] == '1' || map[r][c] == '1' || map[r + 1][c] == '1')
				return false;
		}
		return true;
	}

	static boolean canRotate(int r, int c) {
		for (int i = r - 1; i <= r + 1; i++) {
			for (int j = c - 1; j <= c + 1; j++) {
				if (i < 0 || i >= N || j < 0 || j >= N)
					return false;
				if (map[i][j] == '1')
					return false;
			}
		}
		return true;
	}
}