import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> answerList;
	public static void main(String ags[]) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		k = sc.nextInt();
		map = new int[m][n];
		visited = new boolean[m][n];
		answerList = new ArrayList();
	
		for(int i=0;i<k;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			
			for(int j = b ; j < d ; j++) {
				for(int l = a ; l < c ; l++) {
					if(map[j][l]==0) {
						map[j][l]= 1;
					}
				}
			}
		}
		
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					int result = bfs(i ,j);
					answerList.add(result);
				}
			}
		}
		
		Collections.sort(answerList);
		StringBuilder sb = new StringBuilder();
		sb.append(answerList.size()+"\n");
		
		for(int i=0;i<answerList.size();i++) {
			sb.append(answerList.get(i));
			sb.append(" ");
			
		}
		System.out.println(sb);
	}
	
	static int bfs( int r , int c) {
		Queue <int[]> q = new ArrayDeque();
		visited[r][c] = true;
		q.offer(new int[] {r,c});
		int result =0;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int rr = p[0];
			int cc = p[1];
			result++;
			for(int i=0;i<4;i++) {
				
				int nr = rr+dr[i];
				int nc = cc+dc[i];
				
				if(nr>=0 && nr < m && nc>=0 && nc<n && map[nr][nc]==0 && !visited[nr][nc]) {
					q.offer(new int[] {nr ,nc});
					visited[nr][nc] = true;
					
				}
			}
		}
		
		return result;
	}
	
	
}
