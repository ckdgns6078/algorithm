import java.util.*;

public class Main {
	static int[][] map;
	static int n;
	static int result = 0;
	static int[][] cloneMap;
	static ArrayList<int[]> list = new ArrayList();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];
		cloneMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int input = sc.nextInt();
				map[i][j] = input;
				cloneMap[i][j] = input;
				result = Math.max(result, map[i][j]);
			}
		}
		permutation(0, new int[5]);

		for (int i = 0; i < list.size(); i++) {
			int[] sel = list.get(i);
			for (int j = 0; j < sel.length; j++) {
				int cnt = sel[j];
				mergeMap(cnt);
			}
			rollBack();
		}

		System.out.println(result);
	}

	// 4개중에 5개 뽑는거
	private static void permutation(int k, int[] sel) {
		if (k == 5) {
			list.add(sel.clone());
			return;
		}
		for (int i = 0; i < 4; i++) {
			sel[k] = i;
			permutation(k + 1, sel);
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
