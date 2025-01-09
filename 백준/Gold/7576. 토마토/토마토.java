import java.util.Scanner;
import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> tomatoList = new ArrayList();
	static int answer = 0;
	static boolean emptyCheck =false;
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					tomatoList.add(new int[] { i, j });
				}
			}
		}
		
		bfs();

		L : for(int i=0; i<n ; i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) {
					emptyCheck = true;
					break L;
				}
			}
		}
		
		if(emptyCheck) {
			System.out.println(-1);
		}else {
			System.out.println(answer-1);
		}
		
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque();
		
		for (int i = 0; i < tomatoList.size(); i++) {
			int r = tomatoList.get(i)[0];
			int c = tomatoList.get(i)[1];
			q.offer(new int[] { r, c });
			visited[r][c] = true;
		}

		while (!q.isEmpty()) {
			
			int size = q.size();
			answer++;
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int rr = p[0];
				int cc = p[1];

				for (int j = 0; j < 4; j++) {
					int nr = rr + dr[j];
					int nc = cc + dc[j];
					
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && map[nr][nc] !=-1) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
						map[nr][nc] = 1;
					}
				}

			}

		}

	}

}
