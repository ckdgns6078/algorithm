import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static long answer;;
	static int[][][] tetrises;
	static int testCase = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		tetrises = new int[5][][];

		while (true) {
			N = sc.nextInt();
			if (N == 0) {
				break;
			}

			answer = Integer.MIN_VALUE;
			map = new int[N][N];
			makeTetris();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int cnt = 0;
			int index = 0;

			while (index < 5) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						findMax(index, i, j);
					}
				}

				turn(index);
				cnt++;
				if (cnt > 3) {
					index++;
					cnt = 0;
				}
			}

			sb.append(testCase).append(". ").append(answer).append("\n");
			testCase++;
		}
		System.out.println(sb.toString());
	}

	public static void findMax(int index, int r, int c) {
		int[][] tetris = tetrises[index];
		int sum = 0;

		for (int i = 0; i < tetris.length; i++) {
			for (int j = 0; j < tetris[0].length; j++) {
				if (tetris[i][j] == 1) {
					if (r + i < 0 || r + i >= N || c + j < 0 || c + j >= N) {
						return;
					}
					sum += map[r + i][c + j];
				}
			}
		}
		answer = Math.max(answer, sum);
	}

	// 초기 테트리스를 만드는 함수
	public static void makeTetris() {
		tetrises[0] = new int[1][4];
		tetrises[1] = new int[2][3];
		tetrises[2] = new int[2][3];
		tetrises[3] = new int[2][3];
		tetrises[4] = new int[2][2];

		tetrises[0][0][0] = 1;
		tetrises[0][0][1] = 1;
		tetrises[0][0][2] = 1;
		tetrises[0][0][3] = 1;

		tetrises[1][0][0] = 1;
		tetrises[1][0][1] = 1;
		tetrises[1][1][1] = 1;
		tetrises[1][1][2] = 1;

		tetrises[2][0][0] = 1;
		tetrises[2][0][1] = 1;
		tetrises[2][0][2] = 1;
		tetrises[2][1][2] = 1;

		tetrises[3][0][0] = 1;
		tetrises[3][0][1] = 1;
		tetrises[3][0][2] = 1;
		tetrises[3][1][1] = 1;

		tetrises[4][0][0] = 1;
		tetrises[4][0][1] = 1;
		tetrises[4][1][0] = 1;
		tetrises[4][1][1] = 1;
	}

	public static void turn(int index) {
		int r = tetrises[index].length;
		int c = tetrises[index][0].length;
		int[][] newTetris = new int[c][r];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				newTetris[j][r - i - 1] = tetrises[index][i][j];
			}
		}

		tetrises[index] = newTetris;
	}
}
