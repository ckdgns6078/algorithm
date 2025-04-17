import java.util.*;

public class Main {

	static int N; // map의 크기
	static int M; // 사람의 수
	static int oil; // 기존의 기름
	static int[][] map;

	static Map<Integer, int[]> endMap;
	static int[] taxi = new int[2];

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		oil = sc.nextInt();

		map = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}

		taxi[0] = sc.nextInt();
		taxi[1] = sc.nextInt();

		endMap = new HashMap();
		for (int i = 1; i <= M; i++) {
			int startR = sc.nextInt();
			int startC = sc.nextInt();
			int endR = sc.nextInt();
			int endC = sc.nextInt();

			map[startR][startC] = i;
			endMap.put(i, new int[] { endR, endC });
		}

		boolean check = true;
		while (true) {
			// 태울 사람이 이제 없을 경우 종료
			if (M == 0) {
				break;
			}
			// 사람을 찾으러 이동
			int[] guest = findGuest();
			if (guest == null) {
				check = false;
				break;
			}
			oil -= guest[2];
			if (oil < 0) {
				check = false;
				break;
			}
			// 찾은 인원은 map에 0으로 포시

			// 도착지로 이동
			int pay = moveEndPoint(guest);

			if (oil - pay < 0 || pay == -1) {
				check = false;
				break;
			}
			map[guest[0]][guest[1]] = 0;
			oil -= pay;
			oil += pay * 2;

		}
		System.out.println(check == false ? -1 : oil);

		// 행이 작고 열이 작은 인원을 먼저 뽑는다.
	}

	public static int[] findGuest() {
		Queue<int[]> q = new ArrayDeque();
		boolean[][] visited = new boolean[N + 1][N + 1];
		q.offer(new int[] { taxi[0], taxi[1], 0 });
		visited[taxi[0]][taxi[1]] = true;

		PriorityQueue<int[]> resultQueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});

		while (!q.isEmpty()) {

			int size = q.size();

			if (resultQueue.size() > 0) {
				// 반환하는 코드 추가
				M--;
				int[] temp = resultQueue.poll();
				return temp;
			}

			for (int i = 0; i < size; i++) {
				int[] p = q.poll();

				int r = p[0];
				int c = p[1];
				if (map[r][c] > 0) {
					resultQueue.offer(new int[] { r, c, p[2] });
				}
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					if (nr >= 1 && nr < N + 1 && nc >= 1 && nc < N + 1 && !visited[nr][nc] && map[nr][nc] != -1) {
						q.offer(new int[] { nr, nc, p[2] + 1 });
						visited[nr][nc] = true;
					}
				}

			}
		}
		if (resultQueue.size() > 0) {
			M--;
			int[] temp = resultQueue.poll();
			return temp;
		}
		return null;
	}

	public static int moveEndPoint(int[] guest) {
		int r = guest[0];
		int c = guest[1];
		int index = map[r][c];
		Queue<int[]> q = new ArrayDeque();
		boolean[][] visited = new boolean[N + 1][N + 1];
		visited[r][c] = true;
		q.offer(new int[] { r, c, 0 });

		int endR = endMap.get(index)[0];
		int endC = endMap.get(index)[1];

		while (!q.isEmpty()) {
			int[] p = q.poll();

			if (endR == p[0] && endC == p[1]) {
				taxi[0] = endR;
				taxi[1] = endC;
				return p[2];
			}

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];

				if (nr >= 1 && nr < N + 1 && nc >= 1 && nc < N + 1 && !visited[nr][nc] && map[nr][nc] != -1) {
					q.offer(new int[] { nr, nc, p[2] + 1 });
					visited[nr][nc] = true;
				}
			}
		}
		return -1;

	}

}
