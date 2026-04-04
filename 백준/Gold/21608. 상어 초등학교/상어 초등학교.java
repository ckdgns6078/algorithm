import java.util.*;

public class Main {

	static int N;
	static int stCnt;
	static int[][] map;
	static boolean[][] visited;
	static Map<Integer, List<Integer>> students = new HashMap();
	static int[] order;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static PriorityQueue<Seat> pq = new PriorityQueue();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		stCnt = N * N;
		map = new int[N][N];
		visited = new boolean[N][N];
		order = new int[stCnt];
		for (int i = 0; i < stCnt; i++) {
			int idx = sc.nextInt();
			order[i] = idx;
			List<Integer> list = new ArrayList();
			for (int j = 0; j < 4; j++) {
				list.add(sc.nextInt());
			}
			students.put(idx, list);
		}

		for (int t = 0; t < stCnt; t++) {
			int idx = order[t];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int r1 = r1(idx, i, j);
						int r2 = r2(idx, i, j);

						pq.offer(new Seat(i, j, r1, r2));
					}
				}
			}
			Seat s = pq.poll();
			map[s.r][s.c] = idx;
			visited[s.r][s.c] = true;
			pq.clear();
		}

		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int result = satisfaction(i,j);
				answer+= resultCheck(result);

			}
		}

		System.out.println(answer);
	}

	public static int resultCheck(int result) {
		if(result == 0) {
			return 0;
		}
		if( result == 1) {
			return 1;
		}
		if(result == 2) {
			return 10;
		}
		if(result == 3) {
			return 100;
		}
		if(result == 4) {
			return 1000;
		}
		return 0;
	}
	
	public static int satisfaction(int r, int c) {
		int result = 0;
		int idx = map[r][c];
		
		
		for(int i=0;i<4;i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			
			int friend = map[nr][nc];
			
			if(students.get(idx).contains(friend)) {
				result++;
			}
			
		}
		
		return result;
	}
	
	
	
	public static int r1(int idx, int r, int c) {

		int result = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}

			List<Integer> friendList = students.get(idx);
			int seat = map[nr][nc];
			if (friendList.contains(seat)) {
				result++;
			}

		}

		return result;
	}

	public static int r2(int idx, int r, int c) {

		int result = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] != 0) {
				continue;
			}
			result++;
		}

		return result;
	}

	static class Seat implements Comparable<Seat> {
		int r;
		int c;
		int r1;
		int r2;

		public Seat(int r, int c, int r1, int r2) {
			this.r = r;
			this.c = c;
			this.r1 = r1;
			this.r2 = r2;

		}

		public int compareTo(Seat o) {

			if (this.r1 != o.r1) {
				return Integer.compare(o.r1, this.r1);
			} else if (this.r2 != o.r2) {
				return Integer.compare(o.r2, this.r2);
			}
			return Integer.compare(this.r, o.r);
		}

	}
}
