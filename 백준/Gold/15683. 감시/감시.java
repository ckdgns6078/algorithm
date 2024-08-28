import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result = Integer.MAX_VALUE;
	static int[][][] move = { 
			{ { 0 } }, 
			{ { 0 }, { 1 }, { 2 }, { 3 } },
			{ { 0, 1 }, { 2 , 3} },
			{ { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } },
			{ { 0, 1, 2 }, { 0, 1, 3 }, { 1, 2, 3 }, { 0, 2, 3 } },
			{ { 0, 1, 2, 3 } } 
			};
	private static ArrayList list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		cctv(0, 0);
		System.out.println(result);
	}

	private static void cctv(int r, int c) {
		if (r>=n) {
			result = Math.min(result, inspectMap());
			return;
		}
		if (c >= m) {
			cctv(r + 1, 0);
			return;
		}
		// 일반 땅 , 그림그린칸 , 벽이 아닐경우
		if (map[r][c] != 0 && map[r][c] != 9 && map[r][c] != 6) {
			for (int i = 0; i < move[map[r][c]].length; i++) {
				ArrayList<int []> list = drowMap(r, c, map[r][c], i);
				cctv(r, c + 1 );
//				clearMap(r, c, map[r][c], i);
				clearMap(list);
			}
		} else {
			cctv(r, c + 1 );
		}

	}

	
	
	
private static void clearMap(ArrayList<int[]> list2) {
		for( int i = 0 ; i < list2.size();i++) {
			map[list2.get(i)[0]][list2.get(i)[1]] = 0;
		}
		
	}

//	private static void clearMap(int r, int c, int data, int idx) {
//		
//		for (int i = 0; i < move[data][idx].length; i++) {
//			int dir = move[data][idx][i];
//			int rr = r + dr[dir];
//			int cc = c + dc[dir];
//			
//			while (rr >= 0 && rr < n && cc >= 0 && cc < m) {
//				if( map[rr][cc]==6) {
//					break;
//				}else if(map[rr][cc]==9) {
//					map[rr][cc]=0;
//				}
//				rr += dr[dir];
//				cc += dc[dir];
//			}
//		}
//		if(r==1 && c==1) {
//			for(int i = 0 ; i < n ; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("==================");
//		}
//
//	}
	
	private static ArrayList drowMap(int r, int c, int data, int idx) {
		ArrayList<int[]> list = new ArrayList();
		for (int i = 0; i < move[data][idx].length; i++) {
			int dir = move[data][idx][i];
			int rr = r + dr[dir];
			int cc = c + dc[dir];
			while (rr >= 0 && rr < n && cc >= 0 && cc < m) {
				if( map[rr][cc]==6) {
					break;
				}else if(map[rr][cc]==0) {
					map[rr][cc]=9;
					list.add(new int[] {rr,cc});
				}
				rr += dr[dir];
				cc += dc[dir];
			}
		}
		return list;

	}
	
	
	

//	private static void drowMap(int r, int c, int data, int idx) {
//		ArrayList<int[]> list = new ArrayList();
//		for (int i = 0; i < move[data][idx].length; i++) {
//			int dir = move[data][idx][i];
//			int rr = r + dr[dir];
//			int cc = c + dc[dir];
//			while (rr >= 0 && rr < n && cc >= 0 && cc < m) {
//				if( map[rr][cc]==6) {
//					break;
//				}else if(map[rr][cc]==0) {
//					map[rr][cc]=9;
//					
//				}
//				rr += dr[dir];
//				cc += dc[dir];
//			}
//		}
//
//	}

	private static void checkMap(int r, int c, int i) {
		// TODO Auto-generated method stub

	}

	private static int inspectMap() {
		int output = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					output++;
				}
			}
		}
		return output;
	}

}