import java.util.*;

public class Main {
	static int N;
	static int K;
	static int[][] map;
	static List<Integer>[][] hourseMap;
	static Map<Integer, int[]> info;

	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	// 오른쪽, 왼쪽, 위 , 아래

	static boolean check = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		map = new int[N + 1][N + 1];
		hourseMap = new ArrayList[N + 1][N + 1];
		info = new HashMap<>();

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
				hourseMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			int[] arr = new int[3];
			arr[0] = sc.nextInt();
			arr[1] = sc.nextInt();
			arr[2] = sc.nextInt();

			info.put(i, arr);
			hourseMap[arr[0]][arr[1]].add(i);
		}

		int turn = 0;
		while (turn <= 1000 && !check) {
			turn++;

			for (int i = 1; i <= K; i++) {
				if (check)
					break;

				int[] hourse = info.get(i);
				int r = hourse[0];
				int c = hourse[1];
				int dir = hourse[2];

				int nr = r + dr[dir];
				int nc = c + dc[dir];

				boolean out = nr < 1 || nr > N || nc < 1 || nc > N;

				if (out) {
					blue(i);
					continue;
				}

				int next = map[nr][nc];

				if (next == 2) {
					blue(i);
				} else if (next == 1) {
					red(i);
				} else {
					white(i);
				}
			}
		}

		System.out.println(check ? turn : -1);
	}

	public static void white(int hourse) {
		int[] arr = info.get(hourse);
		int r = arr[0];
		int c = arr[1];
		int dir = arr[2];
		int idx = 0;

		int nr = r + dr[dir];
		int nc = c + dc[dir];

		for (int i = 0; i < hourseMap[r][c].size(); i++) {
			if (hourse == hourseMap[r][c].get(i)) {
				idx = i;
				break;
			}
		}

		for (int i = idx; i < hourseMap[r][c].size(); i++) {
			int hor = hourseMap[r][c].get(i);
			int[] infoArr = info.get(hor);
			infoArr[0] = nr;
			infoArr[1] = nc;
			hourseMap[nr][nc].add(hor);

			if (hourseMap[nr][nc].size() >= 4) {
				check = true;
				return;
			}
		}

		for (int i = hourseMap[r][c].size() - 1; i >= idx; i--) {
			hourseMap[r][c].remove(i);
		}
	}

	public static void red(int hourse) {
		int[] arr = info.get(hourse);

		int r = arr[0];
		int c = arr[1];
		int dir = arr[2];

		int nr = r + dr[dir];
		int nc = c + dc[dir];

		int idx = 0;
		for (int i = 0; i < hourseMap[r][c].size(); i++) {
			if (hourseMap[r][c].get(i) == hourse) {
				idx = i;
				break;
			}
		}

		for (int i = hourseMap[r][c].size() - 1; i >= idx; i--) {
			int hor = hourseMap[r][c].get(i);
			int[] infoArr = info.get(hor);
			infoArr[0] = nr;
			infoArr[1] = nc;
			hourseMap[nr][nc].add(hor);

			if (hourseMap[nr][nc].size() >= 4) {
				check = true;
				return;
			}
		}

		for (int i = hourseMap[r][c].size() - 1; i >= idx; i--) {
			hourseMap[r][c].remove(i);
		}
	}

	public static void blue(int hourse) {
		int[] arr = info.get(hourse);

		int r = arr[0];
		int c = arr[1];
		int dir = arr[2];

		int ndir = changeBlue(dir);
		arr[2] = ndir;

		int nr = r + dr[ndir];
		int nc = c + dc[ndir];

		boolean out = nr < 1 || nr > N || nc < 1 || nc > N;
		if (out || map[nr][nc] == 2) {
			return;
		}

		if (map[nr][nc] == 0) {
			white(hourse);
		} else {
			red(hourse);
		}
	}

	public static int changeBlue(int dir) {
		switch (dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return 0;
	}
}
