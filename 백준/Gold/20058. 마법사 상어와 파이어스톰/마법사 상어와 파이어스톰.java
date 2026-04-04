import java.util.*;

public class Main {
	static int N;
	static int Q;
	static int[] qlist;
	static int[][] map;
	static int[][] temp;

	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum =0;
		int answer =0;
		N = sc.nextInt();
		Q = sc.nextInt();

		int pow = (int) Math.pow(2, N);
		map = new int[pow][pow];
		visited = new boolean[pow][pow];
		for (int i = 0; i < pow; i++) {
			for (int j = 0; j < pow; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		qlist = new int[Q];
		for (int i = 0; i < Q; i++) {
			qlist[i] = sc.nextInt();
		}

		for (int i = 0; i < Q; i++) {

			int q = qlist[i];
			// 회전한다.
			rotate(q, pow);
			// 크기를 맵 얼음 감소
			iceCheck(pow);
		}
		// bfs 크기 호출
		
		for(int i=0;i<pow;i++) {
			for(int j=0;j<pow;j++) {
				sum += map[i][j];
				if(!visited[i][j] && map[i][j] > 0) {
					answer = Math.max(answer, bfs(i,j,pow));
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(answer);
	}

	public static int bfs(int r, int c , int pow) {
		
		int answer =0;
		visited[r][c] = true;
		Queue<int[]> q= new ArrayDeque();
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			answer++;
			
			for(int i=0;i<4;i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if(nr < 0 || nc < 0 || nr>=pow || nc >=pow || visited[nr][nc] || map[nr][nc]==0) {
					continue;
				}
				q.offer(new int[] {nr,nc});
				visited[nr][nc] = true;
			}
		}
		
		return answer;
	}
	
	
	public static void rotate(int q, int pow) {
		temp = new int[pow][pow];
		int size = (int) Math.pow(2, q); // -> 크기

		for (int r = 0; r < pow; r += size) {
			for (int c = 0; c < pow; c += size) {

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						temp[r + j][c + size - 1 - i] = map[r + i][c + j];
					}
				}
			}
		}

		map = temp;
	}

	public static void iceCheck(int pow) {
		
		List<int[]> meltList = new ArrayList();
		//4방향에서 얼음이 있는칸과 닿지 않을 경우 얼음이 -1감소한다.
		for (int i = 0; i < pow; i++) {
			for (int j = 0; j < pow; j++) {
				if(map[i][j] == 0) {
					continue;
				}
				
				int cnt = 0;
				for(int k=0;k<4;k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					
					if(nr<0 || nc < 0 || nr >= pow || nc >=pow || map[nr][nc] ==0) {
						continue;
					}
					
					cnt++;
				}
				if(cnt <3) {
					meltList.add(new int[] {i,j});
				}
				
			}
		}
		
		for(int[] melt : meltList) {
			map[melt[0]][melt[1]]--;
		}
	}
}
