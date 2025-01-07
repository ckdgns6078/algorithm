import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static boolean[][] waterVisited;
	static char[][] map;
	static int r, c;
	static List<int[]> waterList = new ArrayList();
	static int[][] points = new int[2][2];
	static int answer = Integer.MAX_VALUE;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();

		map = new char[r][c];
		visited = new boolean[r][c];
		waterVisited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'D') {
					points[0][0] = i;
					points[0][1] = j;
				} else if (map[i][j] == 'S') {
					points[1][0] = i;
					points[1][1] = j;
				} else if (map[i][j] == '*') {
					waterList.add(new int[] { i, j });
				}
			}
		}

		bfs();
		if (answer == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}

	}

	// point 0 : D , point 1 : S
	static void bfs() {
		// 고슴도치 시작위치 정의
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { points[1][0], points[1][1], 0 });
		visited[points[1][0]][points[1][1]] = true;

		// 물의 시작위치 정의
		Queue<int[]> waterQueue = new ArrayDeque<int[]>();
		for (int i = 0; i < waterList.size(); i++) {
			int waterR = waterList.get(i)[0];
			int waterC = waterList.get(i)[1];
			waterQueue.offer(new int[] { waterR, waterC });
			waterVisited[waterR][waterC] = true;
		}

		while (!q.isEmpty()) {

			// 물 이동 시작하기
			int waterSize = waterQueue.size();
			for (int i = 0; i < waterSize; i++) {
				int[] water = waterQueue.poll();
				int wr = water[0];
				int wc = water[1];

				for (int j = 0; j < 4; j++) {
					int nr = wr + dr[j];
					int nc = wc + dc[j];
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] != 'D' && map[nr][nc] != 'X'
							&& waterVisited[nr][nc] == false) {
						waterQueue.offer(new int[] { nr, nc });
						waterVisited[nr][nc] = true;
						map[nr][nc] = '*';
					}
				}

			}
			// 고슴도치 이동 시작
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int[] p = q.poll();
				int pr = p[0];
				int pc = p[1];
				int pPoint = p[2];

				if (map[pr][pc] == 'D') {
					answer = pPoint;
					break;
				}

				for (int j = 0; j < 4; j++) {
					int nr = pr + dr[j];
					int nc = pc + dc[j];
					int nPoint = pPoint + 1;
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && visited[nr][nc] == false && map[nr][nc] != '*'
							&& map[nr][nc] != 'X') {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc, nPoint });
					}
				}
			}
		}

	}
}
