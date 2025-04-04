import java.util.*;

public class Main {
	static int N, M;
	static int[] dr = { 1, 1, 1 };
	static int[] dc = { -1, 0, 1 };
	static int[][] map;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        //입력부
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
        //출력
		System.out.println(bfs());

	}

    //실행 메인 로직부
	public static int bfs() {
		int result = Integer.MAX_VALUE;

		Queue<int[]> q = new ArrayDeque();
		for (int i = 0; i < M; i++) {
			q.offer(new int[] { 0, i, 4, map[0][i] });
		}

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			int before = p[2];
			int score = p[3];

            //마지막에 도착을 하면 가장 작은 값을 return
			if (r == N - 1) {
				result = Math.min(score, result);
				continue;
			}

			for (int i = 0; i < 3; i++) {
				//이전에 이동한 경로의 경우 이동하지 않는다.
                if (before == i) {
					continue;
				}
				int nr = r + dr[i];
				int nc = c + dc[i];
                
                //아래로 이동할 수 있을 경우 이동한다.
				if (nr < N && nc >= 0 && nc < M) {
					q.offer(new int[] { nr, nc, i, score + map[nr][nc] });
				}
			}
		}
		return result;
	}
}
