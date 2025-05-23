import java.util.*;

public class Main {

	static int n;
	static int m;
	static int[][] map;

	static int[] dr = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dc = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] cr = { -1, -1, 1, 1 }; // 대각선
	static int[] cc = { -1, 1, 1, -1 };

	static List<int[]> cloudList;
	static int[][] orders;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n + 1][n + 1];
		cloudList = new ArrayList<>();
		orders = new int[m][2];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < m; i++) {
			orders[i][0] = sc.nextInt();
			orders[i][1] = sc.nextInt();
		}

		// 초기 구름
		cloudList.add(new int[] { n, 1 });
		cloudList.add(new int[] { n, 2 });
		cloudList.add(new int[] { n - 1, 1 });
		cloudList.add(new int[] { n - 1, 2 });

		for (int[] order : orders) {
			moveCloud(order);
		}

		int answer = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

	public static void moveCloud(int[] order) {
		int d = order[0];
		int s = order[1];

		boolean[][] wasCloud = new boolean[n + 1][n + 1];
		List<int[]> newCloudList = new ArrayList<>();
		List<int[]> rainPositions = new ArrayList<>();

		// 1. 구름 이동 및 비 내림
		for (int[] cloud : cloudList) {
			int r = cloud[0];
			int c = cloud[1];

			// s칸 이동 (격자 벗어나면 반대편으로)
			r = ((r + dr[d] * s - 1) % n + n) % n + 1;
			c = ((c + dc[d] * s - 1) % n + n) % n + 1;

			map[r][c] += 1;
			wasCloud[r][c] = true;
			rainPositions.add(new int[] { r, c });
		}

		// 2. 물복사 버그
		for (int[] pos : rainPositions) {
			int r = pos[0];
			int c = pos[1];
			int waterPlus = 0;
			for (int i = 0; i < 4; i++) {
				int nr = r + cr[i];
				int nc = c + cc[i];
				if (nr >= 1 && nr <= n && nc >= 1 && nc <= n) {
					if (map[nr][nc] > 0) waterPlus++;
				}
			}
			map[r][c] += waterPlus;
		}

		// 3. 구름 생성
		cloudList = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (map[i][j] >= 2 && !wasCloud[i][j]) {
					map[i][j] -= 2;
					cloudList.add(new int[] { i, j });
				}
			}
		}
	}
}
