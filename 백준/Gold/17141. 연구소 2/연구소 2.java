import java.util.*;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int M;
	static boolean[][] visited;
	static ArrayList<int[]> virusList = new ArrayList();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					virusList.add(new int[] { i, j });
					map[i][j] = 0;
				}
			}
		}

		combination(0, 0, new int[M]);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : (answer-1));
		
	}

	public static void bfs(int[] sel) {
		int result = 0;
		visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque();

		for (int i = 0; i < M ; i++) {
			int[] arr = virusList.get(sel[i]);
			int r = arr[0];
			int c = arr[1];
 			q.offer(new int[] { r,c });
			visited[r][c] = true;
		}

		while (!q.isEmpty()) {

			int size = q.size();
			
			if (result > answer) {
				return;
			}
			result++;
			
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int r = p[0];
				int c = p[1];

				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1 && !visited[nr][nc]) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}

		}
		if(mapCheck()) {
			answer = Math.min(result, answer);
		}

	}

	public static boolean mapCheck() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public static void combination(int idx, int k, int[] sel) {
		if (k == sel.length) {
			bfs(sel);
			return;
		}
		if (idx == virusList.size()) {
			return;
		}
		sel[k] = idx;
		combination(idx + 1, k + 1, sel);
		combination(idx + 1, k, sel);

	}

}
