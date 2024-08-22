
import java.util.*;

public class Main {
	static int r, c;
	static boolean[][] v;
	static char[][] map;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		map = new char[r][c];
		v = new boolean[r][c];
		int result = 0;

		for (int i = 0; i < r; i++) {
			String data = sc.next();
			for (int j = 0; j < c; j++) {
				map[i][j] = data.charAt(j);
			}
		}
		
		for (int i = 0; i < r; i++) {
			if (map[i][0] == '.') {
				if (func(i, 0)) {
					result++;
				}
			}

		}
		
		
		System.out.println(result);
	}

	private static boolean func(int rr, int cc) {
		//이동하는 곳을 x 로 변경한다.
		map[rr][cc]='x';
		
		
		//마지막에 도착할 경우 ( 도착지점 )
		
		if( cc == c-1) {
			return true;
		}
		
		// 위쪽부터 아래쪽으로 검사한다.
		for( int i = 0 ; i < 3 ; i++) {
			int nr = rr+dr[i];
			int nc = cc+dc[i];

			if(nr>=0 && nr < r && nc >=0 && nc < c && map[nr][nc]=='.') {

				if(func(nr,nc)) { 
					return true;
				}
			}
		}
		return false;
	}

}
