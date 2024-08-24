
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

public class Solution {
	static int[] dr = { 0, 0, 1, 0, -1 }; // 움직임
	static int[] dc = { 0, -1, 0, 1, 0 }; // 움직임

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int m = sc.nextInt();
			int a = sc.nextInt();

			int[][] move = new int[2][m + 1];
			ArrayList<ChargerInfo> chargerList = new ArrayList();
			int result = 0;
			int[][] location = { { 1, 1 }, { 10, 10 } };

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
				location[0][0] = ar;
				location[0][1] = ac;
				location[1][0] = br;
				location[1][1] = bc;
				// location에 이동한 값으로 변경하는 코드 추가해야함
				ArrayList<Integer> alist = new ArrayList();
				ArrayList<Integer> blist = new ArrayList();
				Map<Integer, Integer> aMap = new HashMap();
				Map<Integer, Integer> bMap = new HashMap();
				int aMax = 0;
				int bMax = 0;
				// 검사를 한다.
				for (int j = 0; j < chargerList.size(); j++) {
					ChargerInfo ci = chargerList.get(j);
					int checkA = Math.abs(ar - ci.r) + Math.abs(ac - ci.c);
					int checkB = Math.abs(br - ci.r) + Math.abs(bc - ci.c);

					if (checkA <= ci.d) {
						alist.add(ci.power);
						aMap.put(ci.power, j);
						aMax = Math.max(aMax, ci.power);
					}
					if (checkB <= ci.d) {
						blist.add(ci.power);
						bMap.put(ci.power, j);
						bMax = Math.max(bMax, ci.power);
					}
				}

				if (alist.size() > 0 || blist.size() > 0) { // 충전을 할 수 있는 경우

					// 같은 구간에 접속 한 경우
					if (aMax == bMax && aMap.get(aMax) == bMap.get(bMax)) {
						if( alist.size() ==1 && blist.size()==1) {
							result+=aMax;
						}else {
							Set<Integer> set = new HashSet();
							for( int data : alist) {
								set.add(data);
							}
							for(int data : blist) {
								set.add(data);
							}
							List<Integer> list = new ArrayList(set);
							Collections.sort(list);
							result+= list.get(list.size()-1);
							result+= list.get(list.size()-2);
							
						}
						
					}else {
						result += aMax;
						result += bMax;
					}
				}
			}
			System.out.println("#" + tc + " " + result);

		}
	}
}