
import java.util.*;

/*
 * 1. 0일경우 x,y의 root가 같은지 검사한다.
 * 2. 1일 경우 union 함수를 실행한다.
 * 3. 0일 경우의 결과를 출력한다.
 */
public class Solution {
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			StringBuilder sb = new StringBuilder();
			int n = sc.nextInt();
			int m = sc.nextInt();
			int index = 0;
			arr = new int[n + 1];
		
			// 초기화
			for (int i = 1; i < n + 1; i++) {
				arr[i] = i;
			}

			// 데이터 입력
			for (int i = 0; i < m; i++) {
				int type = sc.nextInt();
				int x = sc.nextInt();
				int y = sc.nextInt();

				if (type == 1) {
					if (find(x) == find(y)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				} else {
					union(x, y);
				}
			}

			System.out.println("#" + tc + " " + sb);

		}
	}

	private static void union(int x, int y) {
		int rx = find(x);
		int ry = find(y);

		if (rx != ry) {
			arr[ry] = rx;
		}

	}

	private static int find(int x) {
		if (arr[x] != x) {
			arr[x] = find(arr[x]);
		}
		return arr[x];
	}

}
