import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static ArrayList<int[]>[] list;
	static int[] dist;
	static boolean[] check;
	static int[][] map;
	static int[] resultArr;
	static int N;
	static int M;
	static int P;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		P = sc.nextInt();

		dist = new int[P + 1];
		check = new boolean[P + 1];
		list = new ArrayList[P + 1];
		resultArr = new int[P+1];
		for (int i = 1; i < P + 1; i++) {
			dist[i] = sc.nextInt();
			list[i] = new ArrayList();
		}

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				if (c == '.') {
					map[i][j] = 0;
				} else if (c == '#') {
					map[i][j] = -1;
				} else {
					int cInt = c - '0';
					map[i][j] = cInt;
					list[cInt].add(new int[] { i, j });
				}
			}
		}

		// 모두 이동할 수 없으면 종료 한다.
		while (true) {
			int temp = 0;
			for (int i = 1; i < P + 1; i++) {
				if (!check[i]) {
					int cnt = bfs(i);
					if (cnt == 0) {
						check[i] = true;
					}
					temp += cnt;
				}
			}
			if (temp == 0) {
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]>0) {
					int cnt = map[i][j];
					resultArr[cnt]++;
				}
			}
		}
		for (int i = 1; i < P + 1; i++) {
			sb.append(resultArr[i]).append(" ");
		}
		System.out.println(sb);

	}

	public static int bfs(int num) {
		int result = 0;
		Queue<int[]> q = new ArrayDeque();

		for (int[] arr : list[num]) {
			q.offer(new int[] { arr[0], arr[1], dist[num] });
		}
		list[num].clear();
		
		while (!q.isEmpty()) {

			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			int d = p[2];
			if (d == 0)
				continue;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int nd = d - 1;
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					q.offer(new int[] { nr, nc, nd });
					list[num].add(new int[] { nr, nc });
					map[nr][nc] = num;
					result++;
				}
			}
		}
		return result;
	}
}
