package test;

import java.util.*;

public class 미세먼지안녕 {
	static int r, c, t;
	static int[][] map;
	static ArrayList<int[]> dustList = new ArrayList();
	static ArrayList<int[]> mDustList = new ArrayList();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] cleaner = new int[2][2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		t = sc.nextInt();

		map = new int[r][c];
		int cnt = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0) {
					dustList.add(new int[] { i, j });
				} else if (map[i][j] == -1) {
					cleaner[cnt][0] = i;
					cleaner[cnt][1] = j;
					cnt++;
				}
			}
		}

		for (int k = 0; k < t; k++) {
			spread();
			
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("-=------------------------------------");
			
			upCirculation();
			downCirculation();
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 먼지 확산
	private static void spread() {
		// list 에서 꺼내서 4방 탐색을 돌린다

		/*
		 * list에 4방 탐색을 돌린다. 옮기고 나서 더해준다
		 */
		for (int i = 0; i < dustList.size(); i++) {
			int[] dust = dustList.get(i);
			int dustValue = map[dust[0]][dust[1]];
			for (int j = 0; j < 4; j++) {
				int nr = dust[0] + dr[j];
				int nc = dust[1] + dc[j];

				int moveValue = (dustValue / 5);
				if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] != -1) {
					mDustList.add(new int[] { nr, nc, moveValue });
					map[dust[0]][dust[1]] -= moveValue;
				}
			}
		}

		// 새로 움직인 먼지 map에 반영하기
		for (int i = 0; i < mDustList.size(); i++) {
			int[] mdust = mDustList.get(i);
			int r = mdust[0];
			int c = mdust[1];
			int value = mdust[2];
			map[r][c] += value;
		}

		dustList.clear();
		mDustList.clear();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					dustList.add(new int[] { i, j });
				}
			}
		}

	}

	private static void upCirculation() {
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int cnt = 0;
		int cr = cleaner[0][0] + dr[cnt];
		int cc = cleaner[0][1] + dc[cnt];
		System.out.println(cr);
		System.out.println(cc);
		while (true) {
			System.out.println("cr : " + cr + " , " + " cc : " + cc + " data : " + map[cr][cc]);
			 
 			if (cr + dr[cnt] < cleaner[0][0] || cr + dr[cnt] >= r || cc+dc[cnt] < 0 || cc+dc[cnt] >= c) {
				cnt++;
				
			}
			if(cnt>=4 || map[cr+dr[cnt]][cc+dc[cnt]]==-1) {
				break;
			}
			map[cr][cc] = map[cr+dr[cnt]][cc + dc[cnt]];
			cr+= dr[cnt];
			cc+= dc[cnt];
			
		}

		// 가로로 쭉 이동하기

		// 이동한 값이 r 보다 작으면 증가한다.

		//

	}

	private static void downCirculation() {
		// TODO Auto-generated method stub

	}

}
