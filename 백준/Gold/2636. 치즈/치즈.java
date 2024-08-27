
import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int result = 0;
	static int temp =0;
	static boolean[][] v;
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
		v= new boolean[n][m];
		
		while (true) {
			
			for( int i = 0 ; i < n ; i++) {
				Arrays.fill(v[i], false);
			}
			
			// 치즈검사한다.
			int deleteCnt =0;
			int cheeseCnt = checkCheese();
			if (cheeseCnt == 0) {
				break;
			}

			sideCheck(0,0 , v);
			

		

			for( int i = 1 ; i < n-1 ; i++) {
				for( int j = 1 ; j < m-1 ; j++) {
					if(map[i][j]==1 && deleteCheese(i,j,v)) {
						map[i][j]=0;
						deleteCnt++;
					}
				}
			}
			temp=deleteCnt;
			result++;

		}
		
		System.out.println(result);
		System.out.println(temp);
	}

	//0과 인접한 치즈 검사
	private static boolean deleteCheese(int r, int c, boolean[][] v) {
		for( int i = 0 ; i < 4 ; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(map[nr][nc]==0 && v[nr][nc]) {
				return true;
			}
		}
		
		return false;
	}

	//bfs돌리기
	private static void sideCheck(int r, int c, boolean[][] v) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		v[r][c] = true;
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for( int i = 0 ; i < 4 ; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if( nr>=0 && nr<n && nc>=0 && nc<m && !v[nr][nc] && map[nr][nc]==0) {
					q.offer(new int[] {nr,nc});
					v[nr][nc] = true;
				}
			}
			
		}
		

	}
 
	//치즈 갯수 검사
	private static int checkCheese() {
		int output = 0;
		for( int i = 1 ; i < n-1 ; i++) {
			for( int j = 1 ; j < m-1 ; j++) {
				if(map[i][j] == 1) {
					output++;
				}
			}
		}
		return output;
	}

}
