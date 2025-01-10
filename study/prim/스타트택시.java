import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 스타트택시 {

	static class Guest implements Comparable<Guest> {
		/*
		 * 도착하는데 이동하는 시간
		 */
		int r;
		int c;
		int er;
		int ec;
		int coast;
		int index;

		Guest(int r, int c, int er, int ec, int coast) {
			this.r = r;
			this.c = c;
			this.er = er;
			this.ec = ec;
			this.coast = coast;
		}

		@Override
		public int compareTo(스타트택시.Guest o) {
			// TODO Auto-generated method stub
			if (this.coast != o.coast) {
				return Integer.compare(this.coast, o.coast);
			}
			if (this.r != this.r) {
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.c, o.c);

		}

		/*
		 * 1. 가장 짧은 거리 2. 행이 가장 작은 3. 열이 가장 작은
		 */

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int n, m;
	static int gas;
	static int[][] map;
	static int[][] guest;
	static int[] taxi = new int[2];
	static boolean check = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		gas = sc.nextInt();
		map = new int[n + 1][n + 1];
		// 맵 입력받기
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		taxi[0] = sc.nextInt();
		taxi[1] = sc.nextInt();

		guest = new int[m][4];
		for (int i = 0; i < m; i++) {
			guest[i][0] = sc.nextInt();
			guest[i][1] = sc.nextInt();
			guest[i][2] = sc.nextInt();
			guest[i][3] = sc.nextInt();
		}

		// 1. priorityQueue 를생성한다.
		// 2. pq를 순서에 맞게 데이터를 넣는다.
		// 3. pq에서 가장 빠른 순서를 꺼낸다.
		// 4. pq를 초기화 한다.
		// 5. pq 에서 이동하도록 계산한다.

		PriorityQueue<Guest> pq = new PriorityQueue();
		PriorityQueue<Guest> tq = new PriorityQueue();
		int cnt = m;
	
		while (gas > 0 && cnt > 0) {
			System.out.println("gas :" + gas);
			if (!tq.isEmpty()) {
				while (!tq.isEmpty()) {
					Guest g = tq.poll();
					int coast = bfs(taxi[0], taxi[1], g.r, g.c);
					pq.offer(new Guest(g.r, g.c, g.er, g.ec, coast));
				}
			} else {
				for (int i = 0; i < m; i++) {
					int r = guest[i][0];
					int c = guest[i][1];
					int er = guest[i][2];
					int ec = guest[i][3];
					int coast = bfs(taxi[0], taxi[1], r, c);
					pq.offer(new Guest(r, c, er, ec, coast));
				}
			}

			// 가장 가까운 사람
			Guest g = pq.poll();
			
			if (!pq.isEmpty()) {
				while (!pq.isEmpty()) {
					tq.offer(pq.poll());
				}
			}

			// 가까운 사람한테 이동
			gas -= g.coast;
			
			if(gas<=0) {
				check = true;
				break;
			}
			
			// 도착지로 이동
			int plus = bfs(g.r, g.c, g.er, g.ec);
			gas = gas - plus + ( plus * 2 );
			
			
			taxi[0] = g.er;
			taxi[1] = g.ec;
			cnt--;

		}
		if(check) {
			System.out.println(-1);
		}else {
			System.out.println(gas);
		}

	}

	/*
	 * 시작지점에서 도착지점까지 가는데 드는 비용을 적는 함수를 만든다
	 */

	static int bfs(int sr, int sc, int er, int ec) {
		if(sr == er && sc == ec) {
			return 0;
		}
		boolean[][] visited = new boolean[n + 1][n + 1];
		visited[sr][sc] = true;

		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { sr, sc, 0 });

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int r = p[0];
			int c = p[1];
			int cnt = p[2];
			if (r == er && c == ec) {
				return cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 1 && nr <= n && nc >= 1 && nc <= n && !visited[nr][nc] && map[nr][nc] != 1) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc, cnt + 1 });
				}
			}

		}
		return 0;
	}

}
