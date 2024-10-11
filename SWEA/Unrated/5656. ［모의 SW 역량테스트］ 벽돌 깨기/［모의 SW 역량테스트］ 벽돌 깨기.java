
import java.util.*;

public class  Solution {

	static int n;
	static int w;
	static int h;
	static ArrayList<int[]> permutations;
	static int[][] copyMap;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
			map = new int[h][w];
			copyMap = new int[h][w];
			
			permutations = new ArrayList();
			result = Integer.MAX_VALUE;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
					copyMap[i][j] = map[i][j];
				}
			}
			
			permutation(0, new int[n]);
			for (int i = 0; i < permutations.size(); i++) {
				for (int j = 0; j < n; j++) {
					shootBall(permutations.get(i)[j]);
					moveWall();
				}
				result = Math.min(result , findMin());
				updateMap();
			}

			System.out.println("#" + tc +" " + result);
		}

	}

	private static int findMin() {
		int sum = 0;
		for(int i = 0 ; i < h ; i++) {
			for(int j = 0 ; j < w ;j++) {
				if(copyMap[i][j]>0) {
					sum++;
				}
				if(sum>result) {
					return sum;
				}
			}
		}
		return sum;
	}

	// 구슬을 쏘는 함수
	private static void shootBall(int c) {
		// c에서 y 값을 계속 마이너스 하다가 0보다 큰 숫자가 나오면 거기서 부터 감사를 한다.
		Queue<int[]> q = new ArrayDeque();
		// 초기 터트릴 값 찾기
		int r = 0;
		while (r < h) {
			if (copyMap[r][c] > 0) {
				q.add(new int[] { r, c  , copyMap[r][c]});
				break;
			}
			r++;
		}
		while (!q.isEmpty()) {
			int[] point = q.poll();
			int pr = point[0];
			int pc = point[1];
			int size = point[2];
			copyMap[pr][pc] = 0;
			
			for(int i = 0 ; i <  4 ; i++) {

				for(int j =1 ; j < size ; j++) {
					int nr = pr + dr[i]*j;
					int nc = pc + dc[i]*j;
					
					if(nr>=0 && nr < h && nc >=0 && nc < w) {
						if(copyMap[nr][nc]>0) {
							q.offer(new int[] {nr , nc , copyMap[nr][nc]});
							copyMap[nr][nc] = 0;
						}
					}else {
						break;
					}
					
				}
			}
		}
	}
	
	private static void updateMap() {
		for(int i = 0 ; i < h ; i++) {
			copyMap[i] = map[i].clone();
		}
	}
	

	// 구슬을 쏘고 움직이는 함수
	private static void moveWall() {
		int height = h - 1;

		// 0일 경우 위쪽을 검사해서 0보다 큰값들을 옮기고 그 값을 0으로 만든다.

		/*
		 * 이전의 숫자를 가지고 있는다. 이전의 숫자+1로 자리르 옮기고 옮긴 자리를 이전 숫자로 설정한다.
		 */

		for (int i = 0; i < w; i++) {
			for (int j = h - 1; j >= 0; j--) {
				if (copyMap[j][i] > 0) {
					int temp = 0;
					for (int k = j+1; k < h; k++) {
						if (copyMap[k][i] > 0) {
							break;
						}
						temp++;
					}
					if (temp > 0) {
						int cnt = copyMap[j][i];
						copyMap[j][i] = 0;
						copyMap[j + temp][i] = cnt;
					}
				}
			}

		}

	}

	// 순열함수
	private static void permutation(int k, int[] sel) {
		if (k == n) {
			permutations.add(sel.clone());
			return;
		}
		for (int i = 0; i < w; i++) {
			sel[k] = i;
			permutation(k + 1, sel);
		}

	}

}
