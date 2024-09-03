import java.util.*;

public class Solution {
	static int n, m;
	static ArrayList<int[]> homeList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			n = sc.nextInt();
			m = sc.nextInt();
			homeList = new ArrayList();
			int size = n / 2 + 1;
			int result = 0;
			
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int input = sc.nextInt();
					if (input == 1) {
						homeList.add(new int[] { i, j });
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					result = Math.max(result , findBest(i , j , size));
				}
			}
			System.out.println("#" + tc +" " + result);

		}
	}

	private static int findBest(int r, int c, int size) {
		int output = 0;
		for (int i = 1; i <= n+1; i++) {
			int cost = i * i + (i - 1) * (i - 1);
			int count = 0;

			for (int j = 0; j < homeList.size(); j++) {
				int range = Math.abs(r - homeList.get(j)[0]) + Math.abs(c - homeList.get(j)[1]);
				if (range < i ) {
					count++;
				}
			}
			if(cost <= count * m) {
				output = Math.max(output, count);
			}
			
		}
		return output;

	}

}
