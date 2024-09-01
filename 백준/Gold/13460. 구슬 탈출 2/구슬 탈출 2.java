import java.util.*;

public class Main {
	static int n, m;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] red = new int[2];
	static int[] blue = new int[2];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] goal= new int[2];
	static boolean redGoal = false;
	static boolean blueGoal = false;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new char[n][m];
		visited = new boolean[n][m][n][m];
		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
				} else if (map[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				} else if(map[i][j] == 'O') {
					goal[0]=i;
					goal[1]=j;
				}
			}
		}
		visited[red[0]][red[1]][blue[0]][blue[1]] = true;
		find(1 , red[0] , red[1]  ,blue[0] , blue[1]);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}

	private static void find(int cnt, int redR, int redC, int blueR, int blueC) {
		if (cnt > 10) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			redGoal = false;
			blueGoal = false;
			int rMove = moveRed(i, redR, redC);
			int bMove = moveBlue(i, blueR, blueC);
			if(blueGoal) {
				continue;
			}else {
				if(redGoal) {
					result = Math.min(result, cnt);
					return;
				}
			}
			
			int rr = redR + (rMove * dr[i]);
			int rc = redC + (rMove * dc[i]);
			int br = blueR + (bMove * dr[i]);
			int bc = blueC + (bMove * dc[i]);

			
			if(visited[rr][rc][br][bc]) {
				continue;
			}
			//R , B 를 이동시킨다.

			map[redR][redC] = '.';
			map[blueR][blueC]= '.';
			map[rr][rc] = 'R';
			map[br][bc] = 'B';
			visited[rr][rc][br][bc] = true;
			map[goal[0]][goal[1]]='O';
	
			find(cnt+1 , rr , rc , br , bc);
			
			map[rr][rc] = '.';
			map[br][bc] = '.';
			map[redR][redC] = 'R';
			map[blueR][blueC]= 'B';
			visited[rr][rc][br][bc]=false;
			map[goal[0]][goal[1]] = 'O';
			
		}

	}
	private static int moveRed(int d , int r , int c) {
		boolean colorCheck = false;
		int cnt = 0;
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		while (nr >= 1 && nr < n - 1 && nc >= 1 && nc < m - 1) {
			if (map[nr][nc] == '#') {
				break;
			} else if (map[nr][nc]=='B') {
				colorCheck = true;
			} else if (map[nr][nc] == 'O') {
				redGoal = true;
			}

			cnt++;
			nr += dr[d];
			nc += dc[d];
		}

		if (colorCheck) {
			return cnt - 1;
		}
		return cnt;
	}
	
	private static int moveBlue(int d , int r , int c) {
		boolean colorCheck = false;
		int cnt = 0;
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		while (nr >= 1 && nr < n - 1 && nc >= 1 && nc < m - 1) {
			if (map[nr][nc] == '#') {
				break;
			} else if (map[nr][nc]=='R') {
				colorCheck = true;
			} else if (map[nr][nc] == 'O') {
				blueGoal = true;
			}

			cnt++;
			nr += dr[d];
			nc += dc[d];
		}

		if (colorCheck) {
			return cnt - 1;
		}
		return cnt;
	}
		
		
	
	
	private static int move(int d, int r, int c, char color) {
		boolean colorCheck = false;
		int cnt = 0;
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		while (nr >= 1 && nr < n - 1 && nc >= 1 && nc < m - 1) {
			if (map[nr][nc] == '#') {
				break;
			} else if (map[nr][nc]=='R' || map[nr][nc]=='B') {
				colorCheck = true;
			} else if (map[nr][nc] == 'O') {
				if (color == 'R') {
					redGoal = true;
				} else {
					blueGoal = true;
				}
			}

			cnt++;
			nr += dr[d];
			nc += dc[d];
		}

		if (colorCheck) {
			return cnt - 1;
		}
		return cnt;
	}

}
