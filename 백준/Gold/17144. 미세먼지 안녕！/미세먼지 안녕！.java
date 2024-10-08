import java.util.*;

public class Main {
	static int r, c, t;
	static int[][] map;
	static ArrayList<int[]> dustList = new ArrayList();
	static ArrayList<int[]> mDustList = new ArrayList();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] cleaner = new int[2][2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		t = sc.nextInt();

		map = new int[r][c];
		int cnt = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0) {
					dustList.add(new int[] { i, j });
				} else if (map[i][j] == -1) {
					cleaner[cnt][0] = i;
					cleaner[cnt][1] = j;
					cnt++;
				}
			}
		}

		for (int k = 0; k < t; k++) {
			spread();
			upCirculation();
			downCirculation();
			findDust();
		}
		System.out.println(sum());
	}

	private static void findDust() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					dustList.add(new int[] { i, j });
				}
			}
		}
	}

	// 먼지 확산
	private static void spread() {
		for (int i = 0; i < dustList.size(); i++) {
			int[] dust = dustList.get(i);
			int dustValue = map[dust[0]][dust[1]];
			int moveValue = (dustValue / 5);
			if (dustValue >= 5) {
				for (int j = 0; j < 4; j++) {
					int nr = dust[0] + dr[j];
					int nc = dust[1] + dc[j];
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] != -1) {
						mDustList.add(new int[] { nr, nc, moveValue });
						map[dust[0]][dust[1]] -= moveValue;
					}
				}
			}
		}
		// 새로 움직인 먼지 map에 반영하기
		for (int i = 0; i < mDustList.size(); i++) {
			int[] mdust = mDustList.get(i);
			int r = mdust[0];
			int c = mdust[1];
			int value = mdust[2];
			map[r][c] += value;
		}

		dustList.clear();
		mDustList.clear();
	}

	private static void upCirculation() {
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int cnt = 0;
		int cr = cleaner[0][0] + dr[cnt];
		int cc = cleaner[0][1] + dc[cnt];
		while (cnt < 4) {
			// 다음 위치 계산
			int nextR = cr + dr[cnt];
			int nextC = cc + dc[cnt];
			// 범위 체크
			if (nextR < 0 || nextR > cleaner[0][0] || nextC < 0 || nextC >= c) {
				cnt++;
			}
			// 종료 조건 체크
			if (cnt >= 4) {
				break;
			}
			if (map[cr + dr[cnt]][cc + dc[cnt]] == -1) {
				map[cr][cc] = 0;
				break;
			}
			// map 업데이트
			map[cr][cc] = map[cr + dr[cnt]][cc + dc[cnt]];
			cr = cr + dr[cnt];
			cc = cc + dc[cnt];
		}
	}

	private static void downCirculation() {
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int cnt = 0;
		int cr = cleaner[1][0] + dr[cnt];
		int cc = cleaner[1][1] + dc[cnt];
		while (cnt < 4) {
			// 다음 위치 계산
			int nextR = cr + dr[cnt];
			int nextC = cc + dc[cnt];
			// 범위 체크
			if (nextR < cleaner[1][0] || nextR >= r || nextC < 0 || nextC >= c) {
				cnt++;
			}
			// 종료 조건 체크
			if (cnt >= 4) {
				break;
			}
			if (map[cr + dr[cnt]][cc + dc[cnt]] == -1) {
				map[cr][cc] = 0;
				break;
			}
			// map 업데이트
			map[cr][cc] = map[cr + dr[cnt]][cc + dc[cnt]];
			cr = cr + dr[cnt];
			cc = cc + dc[cnt];
		}
	}

	private static int sum() {
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}

}
