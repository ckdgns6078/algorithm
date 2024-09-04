import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[][] map = new int[n + 1][n + 1];
		int[][] prefix = new int[n + 1][n + 1];
		int[][] arr = new int[m][4];

		// 1부터 시작한다.

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				int num1 = prefix[i - 1][j]; // 더하기
				int num2 = prefix[i][j - 1]; // 더하기
				int num3 = prefix[i - 1][j - 1];// 빼기

				prefix[i][j] = num1 + num2 - num3 + map[i][j];
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < m; i++) {
			int y1 = arr[i][0]; // 
			int x1 = arr[i][1]; // 시작
			int y2 = arr[i][2]; // 도착
			int x2 = arr[i][3]; // 도착
			long result = prefix[y2][x2];
			

			result = result - prefix[y1 - 1][x2] - prefix[y2][x1 - 1] + prefix[y1-1][x1 - 1];
			System.out.println(result);
		}
		sc.close();

		/*
		 * 1. 구간합을 저장하는 배열을 생성한다. 2. 입력받은 값을 통해 구간합에 있는 배열값을 추출한다. 3. 출력한다.
		 */

	}

}
