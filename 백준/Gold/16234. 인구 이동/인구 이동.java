import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N, L, R;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int answer = 0;
	static int[][] marking;
	static boolean[][] visited;
	static Stack<int[]> stack;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		map = new int[N][N];
		stack = new Stack();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

	
		while (true) {
			visited = new boolean[N][N];
			boolean isMove = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int sum = bfs(i, j);
						int size = stack.size();
						if (size > 1) {
							isMove = true;
						}
						
						while(!stack.isEmpty()) {
							int[] p = stack.pop();
							map[p[0]][p[1]] = sum / size;
						}
						

					}
				}
			}

			answer++;
			if(!isMove) {
				break;
			}
		}
		System.out.println(answer-1);

	}

	public static int bfs(int y, int x) {
		stack.push(new int[] { y, x });
		int sum = map[y][x];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { y, x });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int r = p[0];
			int c = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					int temp = Math.abs(map[r][c] - map[nr][nc]);
					if (temp >= L && temp <= R) {
						q.offer(new int[] { nr, nc });
						stack.push(new int[] { nr, nc });
						visited[nr][nc] = true;
						sum += map[nr][nc];
					}

				}
			}
		}
		return sum;
	}

}
