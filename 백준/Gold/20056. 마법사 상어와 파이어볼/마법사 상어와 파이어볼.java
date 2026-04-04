import java.util.*;

public class Main {

	static int N;
	static int M;
	static int K;
	static List<Integer>[][] map;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int[] d1 = { 0, 2, 4, 6 };
	static int[] d2 = { 1, 3, 5, 7 };

	static Map<Integer, Node> fMap = new HashMap();
	static Set<Integer> set = new HashSet();
	static int idx = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList();
			}
		}

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			set.add(idx);
			fMap.put(idx, new Node(r, c, m, s, d));
			map[r][c].add(idx);
			idx++;

		}

		int temp = 0;
		while (temp < K) {
			// 이동한다.
			for (int idx : set) {
				move(idx);
			}

			// 2개 이상 검사를 한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() >= 2) {
						split(i, j);
					}
				}
			}
			temp++;
		}

		int answer = 0;
		for( int i : set) {
			answer += fMap.get(i).m;
		}

		System.out.println(answer);
	}

	// 이동
	public static void move(int idx) {
		Node n = fMap.get(idx);
		int r = n.r;
		int c = n.c;

		map[r][c].remove(Integer.valueOf(idx));

		int nextR = (r + dr[n.d] * (n.s % N) + N) % N;
		int nextC = (c + dc[n.d] * (n.s % N) + N) % N;

		map[nextR][nextC].add(idx);
		fMap.put(idx, new Node(nextR, nextC, n.m, n.s, n.d));
	}

	public static void split(int r, int c) {
		int sumM =0;
		int sumS = 0;
		int size = map[r][c].size();
		
		boolean dirCheck = dirCheck(r,c);
		
		
		for(int idxInCell : map[r][c]) {
			Node n = fMap.get(idxInCell);
			sumM += n.m;
			sumS += n.s;
			
			fMap.remove(idxInCell);
			set.remove(idxInCell);
		}
		map[r][c].clear();
		
		int newM = sumM/5;
		if(newM == 0) {
			return;
		}
		
		int newS = sumS /size;
		int[] nextDirs = dirCheck ? d1 : d2;
		
		for(int i=0;i<4;i++) {
			fMap.put(idx, new Node(r,c,newM,newS,nextDirs[i]));
			set.add(idx);
			map[r][c].add(idx);
			idx++;
		}
		
	}

	public static boolean dirCheck(int r, int c) {
		int temp = 0;
		int temp2 = 0;
		for (int i = 0; i < map[r][c].size(); i++) {
			int idx = map[r][c].get(i);
			int t = fMap.get(idx).d;

			if (t % 2 == 0) {
				temp++;
			} else {
				temp2++;
			}
		}

		if (temp == map[r][c].size() || temp2 == map[r][c].size()) {
			return true;
		}
		return false;
	}

	static class Node {
		int r;// 위치
		int c;// 위치
		int m;// 질량
		int s;// 속력
		int d;// 방향

		public Node(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
