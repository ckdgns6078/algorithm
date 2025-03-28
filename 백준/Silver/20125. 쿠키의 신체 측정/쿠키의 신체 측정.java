import java.util.*;

public class Main {

	static char[][] map;
	static int N;
	static List<int[]> cookies;

	static int[] dr = { -1, 1, 0, 0 }; // 세로
	static int[] dc = { 0, 0, 1, -1 }; // 가로

	static int wr = 0;
	static int wc = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		cookies = new ArrayList();
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				map[i][j] = c;
				if (map[i][j] == '*') {
					cookies.add(new int[] { i, j });
				}
			}
		}

		int mr = 0;
		int mc = 0;

		// 심장 찾기
		for (int[] cookie : cookies) {
			if (middleCheck(cookie[0], cookie[1])) {
				mr = cookie[0];
				mc = cookie[1];
				break;
			}
		}

		int leftArm = findLength(mr, mc, 3);
		int rightArm = findLength(mr, mc, 2);
		int waist = findLength(mr, mc, 5);
		int leftLeg = findLength(wr + 1, wc - 1, 1);
		int rightLeg = findLength(wr + 1, wc + 1, 1);

		System.out.println((mr+1) + " " + (mc+1));
		System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
	}

	public static int findLength(int r, int c, int d) {
		int length = 0;
		boolean check = false;

		if (d == 1) {
			length = 1;
		}
		if (d == 5) {
			d = 1;
			check = true;
		}

		while (true) {
			r += dr[d];
			c += dc[d];

			if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] == '_') {
				break;
			}

			length++;
		}

		if (check) {
			wr = r - dr[d];
			wc = c - dc[d];
		}
		return length;

	}

	public static boolean middleCheck(int r, int c) {
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (map[nr][nc] == '_') {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

}
