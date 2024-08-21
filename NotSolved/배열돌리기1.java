import java.util.*;

public class 배열돌리기1 {
	static int n, m, r;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int cycle = Math.min(n, m) / 2;

		for (int k = 0; k < r; k++) {
			boolean[][] v = new boolean[n][m];
			for (int c = 0; c < cycle; c++) {
				int rr = c;
				int cc = c;
				int cnt = 0;
				int data = 0;
				while (cnt < 4) {

					int br = rr - dr[cnt];
					int bc = cc - dc[cnt];
					System.out.println("br :" + br + " bc :" + bc);
					if (br >= c && br < n-c && bc >= c && bc < n-c) {
						data = map[br][bc];
						map[rr][cc] = data;
						v[rr][cc] = true;
					}
					br = rr + dr[cnt];
					bc = cc + dc[cnt];

					if (br < c || br >= n-c || bc < c || bc >= m-c) {
						cnt++;
					}
					rr+=dr[cnt];
					cc+=dc[cnt];
					
				}

			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

	}

}
