import java.util.*;

public class Main {
	public static int[][] map = new int[9][9];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			String input = sc.next();
			for (int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		solve(0, 0);
	}

	public static void solve(int r, int c) {
		if (r == 9) {
			printSolution();
			System.exit(0);
		}
		int nextR = (c == 8) ? r + 1 : r;
		int nextC = (c == 8) ? 0 : c + 1;

		if (map[r][c] != 0) {
			solve(nextR, nextC);
			return;
		}

		for (int num = 1; num <= 9; num++) {
			if (isValid(r, c, num)) {
				map[r][c] = num;
				solve(nextR, nextC);
				map[r][c] = 0;
			}
		}
	}

	public static boolean isValid(int r, int c, int num) {
		for (int i = 0; i < 9; i++) {
			if (map[r][i] == num || map[i][c] == num) {
				return false;
			}
		}
		int startR = (r / 3) * 3;
		int startC = (c / 3) * 3;
		for (int i = startR; i < startR + 3; i++) {
			for (int j = startC; j < startC + 3; j++) {
				if (map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	public static void printSolution() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
