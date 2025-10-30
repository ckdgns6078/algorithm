import java.util.*;

public class Main {

	static int M;
	static int N;
	static int P;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][][] visited;

	static int bossR;
	static int bossC;
	static int bossHp;

	static Map<Character, Integer> playerMap = new HashMap<>();
	static Set<Character> attackerSet = new HashSet<>();

	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		P = sc.nextInt();

		List<Player> playerList = new ArrayList<>();

		map = new char[M][N];
		visited = new boolean[26][M][N];

		for (int i = 0; i < M; i++) {
			String input = sc.next();
			for (int j = 0; j < N; j++) {
				char c = input.charAt(j);
				map[i][j] = c;
				if (c == 'B') {
					bossR = i;
					bossC = j;
				} else if (c != '.' && c != 'X') {
					playerList.add(new Player(i, j, c));
				}
			}
		}

		for (int i = 0; i < P; i++) {
			char name = sc.next().charAt(0);
			int dps = sc.nextInt();
			playerMap.put(name, dps);
		}

		bossHp = sc.nextInt();

		run(playerList);
		System.out.println(answer);
	}

	public static void run(List<Player> playerList) {
		Queue<Player> q = new ArrayDeque<>();

		for (Player p : playerList) {
			q.offer(p);
			visited[p.name - 'a'][p.r][p.c] = true;
			if (p.r == bossR && p.c == bossC) {
				attackerSet.add(p.name);
			}
		}

		int time = 1;
		while (true) {
			for (char idx : attackerSet) {
				bossHp -= playerMap.get(idx);
			}

			if (bossHp <= 0) {
				answer = attackerSet.size();
				return;
			}

			int size = q.size();
			for (int k = 0; k < size; k++) {
				Player p = q.poll();
				int r = p.r;
				int c = p.c;
				char name = p.name;

				if (r == bossR && c == bossC) {
					attackerSet.add(name);
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (nr < 0 || nc < 0 || nr >= M || nc >= N)
						continue;
					if (map[nr][nc] == 'X' || visited[name - 'a'][nr][nc])
						continue;

					visited[name - 'a'][nr][nc] = true;
					q.offer(new Player(nr, nc, name));
				}
			}

			time++;
		}
	}

	static class Player {
		int r;
		int c;
		char name;

		Player(int r, int c, char name) {
			this.r = r;
			this.c = c;
			this.name = name;
		}
	}
}
