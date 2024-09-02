import java.util.*;

class Honey {
	int sum;
}

public class Solution {

	static int fsum = 0;
	static int n;
	static int m;
	static int c;
	static int[] resultList;
	static int[][] map;
	static int result =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			n = sc.nextInt(); // map size
			m = sc.nextInt(); // width
			c = sc.nextInt(); // size
			resultList = new int[n];
			map = new int[n][n];
			result =0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n - m; j++) {
					int[] arr = new int[m];
					int cnt = 0;
					
					for (int k = j; k < m + j; k++) {
						arr[cnt] = map[i][k];
						cnt++;
					}
					fsum = 0;
					subset(0, arr, new boolean[m]);
					int res1 = fsum;
					findMax(i, j);
					int res2 = fsum;
					result = Math.max(result, res1+res2);
				}
			}
			
			System.out.println("#"+ tc +" " + result);

		}

	}

	// 부분집합을 만들어서 가장 큰 값을 찾는 함수
	private static void subset(int idx, int[] arr, boolean[] v) {
		if (idx == arr.length) {
			int sum = 0;
			int num = 0;
			for (int i = 0; i < v.length; i++) {
				if (v[i] == true) {
					num += arr[i];
					sum += Math.pow(arr[i], 2);
				}
			}
			if (num <= c) {
				fsum = Math.max(fsum, sum);
			}
			return;
		}

		v[idx] = true;
		subset(idx + 1, arr, v);
		v[idx] = false;
		subset(idx + 1, arr, v);

	}

	private static void findMax(int r, int c) {
		fsum = 0;
//		for (int j = c + 2; j <= n - m; j++) {
//			int[] arr = new int[m];
//			int cnt =0;
//			for (int k = j; k < m + j; k++) {
//				arr[cnt] = map[r][k];
//				cnt++;
//			}
//			subset(0, arr, new boolean[m]);	
//		}
		
		
		for(int i = r+1 ; i < n ;i++) {
			for(int j = 0 ; j <= n-m ; j++) {
				int[] arr = new int[m];
				int cnt =0;
				for(int k = j ; k < m+j ; k++) {
					arr[cnt] = map[i][k];
					cnt++;
				}
				subset(0, arr, new boolean[m]);
			}
		}
		
	}

}
