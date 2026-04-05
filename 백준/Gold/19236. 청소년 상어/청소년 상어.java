import java.util.*;

public class Main {

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int[][] map;
	static Fish[] fishes;
	static boolean[] fishArrived;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[4][4];
		fishes = new Fish[17];
		fishArrived = new boolean[17];
		Arrays.fill(fishArrived, true);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int idx = sc.nextInt();
				int dir = sc.nextInt() - 1;
				map[i][j] = idx;
				fishes[idx] = new Fish(i, j, dir);
			}
		}

		int catchIdx = map[0][0];
		Fish fish = fishes[catchIdx];
		Shark shark = new Shark(0, 0, fish.dir, catchIdx);
		fishArrived[catchIdx] = false;
		map[0][0] = -1;

		dfs(shark);
		System.out.println(answer);
	}

	public static void dfs(Shark shark) {
		answer = Math.max(answer, shark.size);

		int[][] originMap = new int[4][4];
		for (int i = 0; i < 4; i++)
			originMap[i] = map[i].clone();
		Fish[] originFishes = new Fish[17];
		for (int i = 1; i <= 16; i++)
			originFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].dir);
		boolean[] originArrived = fishArrived.clone();

		fishMove(shark);

		int[][] movedMap = new int[4][4];
		for (int i = 0; i < 4; i++)
			movedMap[i] = map[i].clone();
		Fish[] movedFishes = new Fish[17];
		for (int i = 1; i <= 16; i++)
			movedFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].dir);
		boolean[] movedArrived = fishArrived.clone();

		List<int[]> sharkMoveList = sharkMoveCheck(shark);

		for (int[] pos : sharkMoveList) {
			int nr = pos[0];
			int nc = pos[1];

			int prevR = shark.r;
			int prevC = shark.c;
			int prevSize = shark.size;
			int prevDir = shark.dir;

			int fishIdx = map[nr][nc];
			int nextDir = fishes[fishIdx].dir;

			map[prevR][prevC] = 0;
			shark.r = nr;
			shark.c = nc;
			shark.dir = nextDir;
			shark.size += fishIdx;
			map[nr][nc] = -1;
			fishArrived[fishIdx] = false;

			dfs(shark);

			shark.r = prevR;
			shark.c = prevC;
			shark.dir = prevDir;
			shark.size = prevSize;

			for (int i = 0; i < 4; i++)
				map[i] = movedMap[i].clone();
			for (int i = 1; i <= 16; i++) {
				fishes[i].r = movedFishes[i].r;
				fishes[i].c = movedFishes[i].c;
				fishes[i].dir = movedFishes[i].dir;
			}
			fishArrived = movedArrived.clone();
		}

		for (int i = 0; i < 4; i++)
			map[i] = originMap[i].clone();
		for (int i = 1; i <= 16; i++) {
			fishes[i].r = originFishes[i].r;
			fishes[i].c = originFishes[i].c;
			fishes[i].dir = originFishes[i].dir;
		}
		fishArrived = originArrived.clone();
	}

	public static void fishMove(Shark shark) {
		for (int i = 1; i <= 16; i++) {
			if (!fishArrived[i])
				continue;

			Fish fish = fishes[i];
			int newDir = fishCheck(fish, shark);
			if (newDir == 10)
				continue;

			fish.dir = newDir;
			int nr = fish.r + dr[newDir];
			int nc = fish.c + dc[newDir];

			int nextIdx = map[nr][nc];

			if (nextIdx == 0) {
				map[fish.r][fish.c] = 0;
				fish.r = nr;
				fish.c = nc;
				map[nr][nc] = i;
			} else {
				Fish nextFish = fishes[nextIdx];
				map[fish.r][fish.c] = nextIdx;
				map[nr][nc] = i;

				nextFish.r = fish.r;
				nextFish.c = fish.c;
				fish.r = nr;
				fish.c = nc;
			}
		}
	}

	public static int fishCheck(Fish fish, Shark shark) {
		for (int i = 0; i < 8; i++) {
			int nDir = (fish.dir + i) % 8;
			int nr = fish.r + dr[nDir];
			int nc = fish.c + dc[nDir];

			if (nr >= 0 && nc >= 0 && nr < 4 && nc < 4 && map[nr][nc] != -1) {
				return nDir;
			}
		}
		return 10;
	}

	public static List<int[]> sharkMoveCheck(Shark shark) {
		List<int[]> moves = new ArrayList<>();
		for (int dist = 1; dist < 4; dist++) {
			int nr = shark.r + dr[shark.dir] * dist;
			int nc = shark.c + dc[shark.dir] * dist;

			if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
				if (map[nr][nc] > 0) {
					moves.add(new int[] { nr, nc });
				}
			} else
				break;
		}
		return moves;
	}

	static class Fish {
		int r, c, dir;

		public Fish(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static class Shark {
		int r, c, size, dir;

		public Shark(int r, int c, int dir, int size) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.size = size;
		}
	}
}