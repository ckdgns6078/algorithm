import java.util.*;

class ChargerInfo {

	int r;
	int c;
	int d;
	int power;

	ChargerInfo(int r, int c, int d, int power) {
		this.r = r;
		this.c = c;
		this.d = d;
		this.power = power;
	}
}

public class 무선충전 {
	static int[][] move; // 인간이 이동하는 리스트
	static int[] dr = { 0 - 1, 0, 1, 0 }; // 움직임
	static int[] dc = { 0, 0, 1, 0, -1 }; // 움직임
	static ArrayList<ChargerInfo> chargerList = new ArrayList();

	static int[][] location = { { 0, 0 }, { 9, 9 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int m = sc.nextInt();
			int a = sc.nextInt();

			move = new int[2][m + 1];

			for (int i = 0; i < 2; i++) {
				for (int j = 1; j < m + 1; j++) {
					move[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < a; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
				int d = sc.nextInt();
				int power = sc.nextInt();
				chargerList.add(new ChargerInfo(r, c, d, power));
			}

			for (int i = 0; i < m + 1; i++) {
				int ar = location[0][0] + dr[move[0][i]];
				int ac = location[0][1] + dc[move[0][i]];
				int br = location[1][0] + dr[move[1][i]];
				int bc = location[1][1] + dc[move[1][i]];
				ArrayList<Integer> alist = new ArrayList();
				ArrayList<Integer> blist = new ArrayList();
				// 검사를 한다.
				for (int j = 0; j < chargerList.size(); j++) {
					ChargerInfo ci = chargerList.get(j);
					int checkA = Math.abs(ar - ci.r) + Math.abs(ac - ci.c);
					int checkB = Math.abs(br - ci.r) + Math.abs(bc - ci.c);
					
					if( checkA <= ci.d) {
						alist.add(ci.power);
					}
					if( checkB <= ci.d) {
						blist.add(ci.power);
					}
				}

			}
		}

	}
}
