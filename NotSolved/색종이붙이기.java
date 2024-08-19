import java.util.*;

public class 색종이붙이기 {

	static int[][] map = new int[10][10];
	static int[] papers = { 0, 5, 5, 5, 5, 5 };
	static ArrayList<int[]> list = new ArrayList();

	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					list.add(new int[] { i, j });
				}
			}
		}

		backtracking(0 , 0);
		System.out.println(min);
	}

	private static void backtracking(int idx , int usePaper) {
		if (idx >= list.size()) {
			min = Math.min(min, usePaper);
			return;
		}
		//색칠한 값을 다시 변경해야되는가?
		for (int i = 1; i < 6; i++) {
			if (checkSize(list.get(idx), i) && map[list.get(idx)[0]][list.get(idx)[1]] == 1) {
				setValue(list.get(idx), i, 2);
				papers[i]--;
				backtracking(idx + 1 , usePaper+1);
				setValue(list.get(idx), i, 1);
				papers[i]++;
			}
			// 값을 초기화 시킨다.
		}
//		backtracking(idx+1 , usePaper);

	}

	private static void setValue(int[] arr, int size, int num) {
		int r = arr[0];
		int c = arr[1];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				map[i][j] = num;
			}
		}
	}

	private static boolean checkSize(int[] arr, int size) {
		int r = arr[0];
		int c = arr[1];

		if (papers[size] == 0) {
			return false;
		}
		if( r + size > 10 || c+ size > 10) {
			return false;
		}
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		papers[size]--;
		return true;
	}

}
