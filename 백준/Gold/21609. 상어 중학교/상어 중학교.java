import java.util.*;

public class Main {
	static int N;
	static int M;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int[][] map;
	static PriorityQueue<Block> pq = new PriorityQueue<Block>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int answer = 0;
		while (true) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 || map[i][j] == -1 || visited[i][j] || map[i][j] == -99) {
						continue;
					}

					bfs(i, j);
				}
			}

			if (pq.size() == 0) {
				break;
			}

			Block b = pq.poll();
			answer += b.size * b.size;

			// 빈칸으로 처리한다.
			for (int[] block : b.list) {
				map[block[0]][block[1]] = -99;
			}

			// 중력이 작동한다.
			gravity();

			int[][] temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = map[j][N - 1 - i];
				}
			}

			map = temp;
			gravity();

			reset();

		}

		System.out.println(answer);

	}

	public static void gravity() {
		for (int i = 0; i < N; i++) { // 가로 열
			int temp = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (map[j][i] == -99) { // 빈칸
					temp++;
				} else if (map[j][i] == -1) { // 검정블럭
					temp = 0;
				} else {
					if (temp > 0) {
						map[j + temp][i] = map[j][i];
						map[j][i] = -99;
					}

				}

			}
		}
	}

	public static void reset() {
		pq.clear();
		visited = new boolean[N][N];

	}

	public static void bfs(int rr, int cc) {
		int color = map[rr][cc];

		List<int[]> rainbowList = new ArrayList<int[]>();
		List<int[]> blockList = new ArrayList<int[]>();

		Queue<int[]> q = new ArrayDeque<int[]>();

		q.offer(new int[] { rr, cc });
		blockList.add(new int[] { rr, cc });
		visited[rr][cc] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int r = p[0];
			int c = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == -1 || visited[nr][nc]
						|| map[nr][nc] == -99) {
					continue;
				}

				if (map[nr][nc] != 0 && map[nr][nc] != color) {
					continue;
				}

				// 무지개일 경우
				if (map[nr][nc] == 0) {
					rainbowList.add(new int[] { nr, nc });
				}
				q.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
				blockList.add(new int[] { nr, nc });

			}
		}

		// 큐에 삽입한다.
		if (blockList.size() < 2) {
			return;
		}
		int size = blockList.size();
		int rainbowSize = rainbowList.size();
		pq.offer(new Block(rr, cc, size, rainbowSize, blockList));

		for (int[] rainbow : rainbowList) {
			visited[rainbow[0]][rainbow[1]] = false;
		}

	}

	static class Block implements Comparable<Block> {
		int r;
		int c;
		int size;
		int rainbow;
		List<int[]> list;

		public Block(int r, int c, int size, int rainbow, List<int[]> list) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.rainbow = rainbow;
			this.list = list;
		}

		public int compareTo(Block o) {
			// 크기 내림차순 (큰 것이 우선)
			if (this.size != o.size) {
				return Integer.compare(o.size, this.size);
			}
			// 무지개 내림차순
			if (this.rainbow != o.rainbow) {
				return Integer.compare(o.rainbow, this.rainbow);
			}
			// 기준행 내림차순
			if (this.r != o.r) {
				return Integer.compare(o.r, this.r);
			}
			// 기준열 내림차순
			return Integer.compare(o.c, this.c);
		}
	}
}