package 백준;

import java.util.Arrays;
import java.util.Scanner;

public class Boj12100 {
	static int[][] map;
	static int n;
	static int[][] cloneMap;
	static int result = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];
		cloneMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				result = Math.max(result, map[i][j]);
			}
		}

		run(0);
//		
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		/*
		 * 한번 이동해서 합쳐진 경우 다시 합쳐지지 않는다.
		 */
		// 위 아래 왼쪽 오른쪽 순

		System.out.println(result);
	}

	private static void run(int cnt) {
		if (cnt >5) {
			return;
		}

		for (int i = 0; i < n; i++) {
			cloneMap[i] = map[i].clone();
		}

		for (int i = 0; i < 4; i++) {
			mergeMap(i);
			run(cnt + 1);
			rollBack();
		}

	}

	private static void rollBack() {
		for (int i = 0; i < n; i++) {
			map[i] = cloneMap[i].clone();
		}

	}

	// 위 아래 왼쪽 오른쪽 순
	private static void mergeMap(int k) {
		if (k == 0) {// 위
			// x는0으로 고정 , y값 증가
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[j][i] == 0) {
						continue;
					}
					int cnt = map[j][i];
					int r = j + 1;
					while (r < n) {
						if (cnt != map[r][i] && map[r][i] > 0) {
							break;
						}
						if (cnt == map[r][i]) {
							map[j][i] = cnt * 2;
							map[r][i] = 0;
							result = Math.max(result, map[j][i]);
							break;
						}
						r++;
					}
				}
			}

		} else if (k == 1) {// 아래
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (map[j][i] == 0) {
						continue;
					}
					int cnt = map[j][i];
					int r = j - 1;
					while (r >= 0) {
						if (cnt != map[r][i] && map[r][i] > 0) {
							break;
						}
						if (cnt == map[r][i]) {
							map[j][i] = cnt * 2;
							map[r][i] = 0;
							result = Math.max(result, map[j][i]);
							break;
						}
						r--;
					}
				}
			}
		} else if (k == 2) {// 왼쪽
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 0) {
						continue;
					}
					int cnt = map[i][j];
					int c = j + 1;
					while (c < n) {
						if (cnt != map[i][c] && map[i][c] > 0) {
							break;
						}
						if (cnt == map[i][c]) {
							map[i][j] = cnt * 2;
							map[i][c] = 0;
							result = Math.max(result, map[i][j]);
							break;
						}
						c++;
					}
				}
			}
		} else if (k == 3) {// 오른쪽
			// y값 고정 , x값 감소
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (map[i][j] == 0) {
						continue;
					}
					int cnt = map[i][j];
					int c = j - 1;
					while (c >= 0) {
						if (cnt != map[i][c] && map[i][c] > 0) {
							break;
						}

						if (cnt == map[i][c]) {
							map[i][j] = cnt * 2;
							map[i][c] = 0;
							result = Math.max(result, map[i][j]);
							break;
						}
						c--;
					}

				}
			}
		}
		moveMap(k);

	}

	private static void moveMap(int k) {
		if (k == 0) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[j][i] == 0) {
						int r = j + 1;
						while (r < n) {
							if (map[r][i] > 0) {
								map[j][i] = map[r][i];
								map[r][i] = 0;
								break;
							}
							r++;
						}
					}
				}
			}

		} else if (k == 1) {
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (map[j][i] == 0) {
						int r = j - 1;
						while (r >= 0) {
							if (map[r][i] > 0) {
								map[j][i] = map[r][i];
								map[r][i] = 0;
								break;
							}
							r--;
						}
					}

				}
			}
		} else if (k == 2) {// 왼쪽
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 0) {
						int c = j + 1;
						while (c < n) {
							if (map[i][c] > 0) {
								map[i][j] = map[i][c];
								map[i][c] = 0;
								break;
							}
							c++;
						}

					}
				}
			}
		} else if (k == 3) {// 오른쪽
			// y값 고정 , x값 감소
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (map[i][j] == 0) {
						int c = j - 1;
						while (c >= 0) {
							if (map[i][c] > 0) {
								map[i][j] = map[i][c];
								map[i][c] = 0;
								break;
							}
							c--;
						}
					}

				}
			}
		}
	}

}
