import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int r, c;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> icebugList;
	static int answer = 0;

	public static void main(String ags[]) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		map = new int[r][c];
		icebugList = new ArrayList();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0) {
					icebugList.add(new int[] { i, j, 0 });
				}
			}
		}

		visited = new boolean[r][c];

		int firstCheck = 0;
		for (int i = 0; i < icebugList.size(); i++) {
			if(!visited[icebugList.get(i)[0]][icebugList.get(i)[1]]) {
				bfs(icebugList.get(i)[0], icebugList.get(i)[1]);
				firstCheck++;				
			}
		}

		if (firstCheck >= 2) {
			System.out.println(0);
		} else {

			while (true) {

				icebugCheck();

				if (icebugList.size() == 0) {
					System.out.println(0);
					break;
				}

				visited = new boolean[r][c];
				int temp = 0;
				for (int i = 0; i < icebugList.size(); i++) {
					int rr = icebugList.get(i)[0];
					int cc = icebugList.get(i)[1];
					if (!visited[rr][cc]) {
						bfs(rr, cc);
						temp++;
					}

				}

				answer++;
				if (temp >= 2) {
					System.out.println(answer);
					break;
				}

			}
		}
	}

	private static void bfs(int rr, int cc) {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { rr, cc });

		while (!q.isEmpty()) {

			int[] p = q.poll();
			int mr = p[0];
			int mc = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = mr + dr[i];
				int nc = mc + dc[i];

				if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] > 0 && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}

		}

	}

	private static void icebugCheck() {
		int temp = icebugList.size() - 1;

		while (temp >= 0) {
			int rr = icebugList.get(temp)[0];
			int cc = icebugList.get(temp)[1];
			int check = 0;

			for (int i = 0; i < 4; i++) {
				int nr = rr + dr[i];
				int nc = cc + dc[i];
				if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] == 0) {
					check++;
				}
			}
			icebugList.get(temp)[2] = check;
			temp--;
		}

		for (int i = icebugList.size() - 1; i >= 0; i--) {
			int rr = icebugList.get(i)[0];
			int cc = icebugList.get(i)[1];
			int cnt = icebugList.get(i)[2];

			map[rr][cc] -= cnt;
			if (map[rr][cc] <= 0) {
				map[rr][cc] = 0;
				icebugList.remove(i);

			}
		}

	}
}
