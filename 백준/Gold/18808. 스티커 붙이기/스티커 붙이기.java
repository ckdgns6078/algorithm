import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] notebook;
	static List<int[][]> stickers = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		notebook = new int[N][M];

		for (int k = 0; k < K; k++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int[][] sticker = new int[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					sticker[i][j] = sc.nextInt();
				}
			}
			stickers.add(sticker);
		}

		for (int[][] sticker : stickers) {
			boolean attached = false;
			for (int rot = 0; rot < 4; rot++) {
				int sr = sticker.length;
				int scc = sticker[0].length;
				if (N >= sr && M >= scc) {
					boolean done = false;
					for (int i = 0; i <= N - sr; i++) {
						for (int j = 0; j <= M - scc; j++) {
							if (canAttach(sticker, i, j)) {
								attachSticker(sticker, i, j);
								attached = true;
								done = true;
								break;
							}
						}
						if (done)
							break;
					}
				}
				if (attached)
					break;
				sticker = rotate(sticker);
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (notebook[i][j] == 1)
					result++;
			}
		}
		System.out.println(result);
	}

	static boolean canAttach(int[][] sticker, int x, int y) {
		int r = sticker.length;
		int c = sticker[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (sticker[i][j] == 1 && notebook[x + i][y + j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	static void attachSticker(int[][] sticker, int x, int y) {
		int r = sticker.length;
		int c = sticker[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (sticker[i][j] == 1) {
					notebook[x + i][y + j] = 1;
				}
			}
		}
	}

	static int[][] rotate(int[][] sticker) {
		int r = sticker.length;
		int c = sticker[0].length;
		int[][] rotated = new int[c][r];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				rotated[j][r - 1 - i] = sticker[i][j];
			}
		}
		return rotated;
	}
}
