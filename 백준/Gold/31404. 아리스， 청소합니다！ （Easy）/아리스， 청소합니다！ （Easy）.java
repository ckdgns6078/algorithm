import java.util.*;

public class Main {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int H, W;
	static int R, C, D;
	static int[][] dust;
	static int[][][] visit;
	static int[][] A, B;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		D = sc.nextInt();

		dust = new int[H][W];
		visit = new int[H][W][4];
		A = new int[H][W];
		B = new int[H][W];


		for (int i = 0; i < H; i++) {
			Arrays.fill(dust[i], 1);
		}

		
		for (int i = 0; i < H; i++) {
			char[] line = sc.next().toCharArray();
			for (int j = 0; j < W; j++) {
				A[i][j] = line[j] - '0';
			}
		}

		// B 입력
		for (int i = 0; i < H; i++) {
			char[] line = sc.next().toCharArray();
			for (int j = 0; j < W; j++) {
				B[i][j] = line[j] - '0';
			}
		}

		int ans = 0;
		int dummy = 0;
		int clear = 0;

		while (true) {
			if (dust[R][C] == 1) {
				dust[R][C] = 0;
				D = (D + A[R][C]) % 4;
				clear++;
				dummy = 0;
			} else {
				D = (D + B[R][C]) % 4;
				if (visit[R][C][D] == clear)
					break;
				visit[R][C][D] = clear;
				dummy++;
			}

			R += dy[D];
			C += dx[D];
			ans++;

			if (R < 0 || R >= H || C < 0 || C >= W)
				break;
		}

		System.out.println(ans - dummy);
	}
}
