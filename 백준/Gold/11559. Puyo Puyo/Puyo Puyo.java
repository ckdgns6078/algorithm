import java.util.*;

public class Main {

	// R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
	static char[][] map = new char[12][6];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static boolean[][] deleteVisited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 12; i++) {
			String input = sc.next();
			for (int j = 0; j < 6; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		boolean check = true;
		int answer = 0;
		while (check) {
		    visited = new boolean[12][6];
		    deleteVisited = new boolean[12][6];
		    boolean temp = false;

		    // ↓ break L / cnt 로직을 완전히 제거하고,
		    //    전 칸을 다 뒤져서 동시에 터질 수 있는 뿌요를 모두 찾아낸다.
		    for (int i = 11; i >= 0; i--) {
		        for (int j = 0; j < 6; j++) {
		            if (map[i][j] != '.') {
		                if (PuyoCheck(i, j, map[i][j])) {
		                    temp = true;
		                    PuyoDelete(i, j, map[i][j]);
		                }
		            }
		        }
		    }

		    if (temp) {
		        gravity();
		        answer++;
		    } else {
		        check = false;
		    }
		}
		System.out.println(answer);
	}

	public static boolean PuyoCheck(int r, int c, char color) {
		// 4방에서 같은 값이 있으면 이동을 하고 이미 검사한 값은 겁사하지않는 방법을 사용한다.
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		int temp = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			temp += size;
			if (temp >= 4) {
				return true;
			}
			for (int t = 0; t < size; t++) {
				int[] p = q.poll();
				int rr = p[0];
				int cc = p[1];

				for (int i = 0; i < 4; i++) {
					int nr = rr + dr[i];
					int nc = cc + dc[i];
					if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == color && !visited[nr][nc]) {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}

			}
		}
		return false;
	}

	// puyo 삭제
	public static void PuyoDelete(int r, int c, char color) {
		// 4방에서 같은 값이 있으면 이동을 하고 이미 검사한 값은 겁사하지않는 방법을 사용한다.
		map[r][c] = '.';
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { r, c });
		deleteVisited[r][c] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			int rr = p[0];
			int cc = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = rr + dr[i];
				int nc = cc + dc[i];
				if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == color && !deleteVisited[nr][nc]) {
					q.offer(new int[] { nr, nc });
					deleteVisited[nr][nc] = true;
					map[nr][nc] = '.';
				}
			}
		}

	}

	public static void gravity() {
		for (int i = 0; i < 6; i++) {
			int empty = 11; // 아래에서부터 채워나갈 위치
			for (int j = 11; j >= 0; j--) {
				if (map[j][i] != '.') {
					if (j != empty) {
						map[empty][i] = map[j][i];
						map[j][i] = '.';
					}
					empty--;
				}
			}
		}
	}

}
