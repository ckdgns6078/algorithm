import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[] papers = { 0, 5, 5, 5, 5, 5 };
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		backtracking(0, 0);
		
		System.out.println(answer== Integer.MAX_VALUE ? -1 : answer);
	}

	static void backtracking(int r, int c) {
		if (r >= 10) {
			answer = Math.min(answer, usePaperCheck());
			return;
 		}
		
		if (map[r][c] == 1) {
			// 색종이를 붙히는 로직
			for (int i = 5; i >= 1; i--) {
				boolean check = false;
				if (paperCheck(r, c, i)) {
					marking(r, c, i, 0);
					check = true;
					
					if (c == 9) {
						backtracking(r + 1, 0);
					} else {
						backtracking(r, c + 1);
					}
					
					if(check) {
						marking(r, c, i, 1);					
					}
				}
			}
		}else {
			if (c == 9) {
				backtracking(r + 1, 0);
			} else {
				backtracking(r, c + 1);
			}
		}
	}

	static boolean paperCheck(int r, int c, int size) {
		int rSize = r + size - 1;
		int cSize = c + size - 1;

		if (papers[size] == 0) {
			return false;
		}
		if (rSize >= 10) {
			return false;
		}
		if (cSize >= 10) {
			return false;
		}

		for (int i = r; i <= rSize; i++) {
			for (int j = c; j <= cSize; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	static void marking(int r, int c, int size, int temp) {
		if(temp == 1) {
			papers[size]++;
		}else {
			papers[size]--;
		}

		int rSize = r + size - 1;
		int cSize = c + size - 1;

		for (int i = r; i <= rSize; i++) {
			for (int j = c; j <= cSize; j++) {
				map[i][j]=temp;
			}
		}
		
	}

	static int usePaperCheck() {
		int result = 0;
		for (int i = 1; i <= 5; i++) {
			result += 5 - papers[i];
		}
		return result;
	}
}
