import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int n;
    static int[] dr ={-1 , 1 , 0 , 0};
    static int[] dc = {0 , 0 ,-1 , 1};
    static boolean[][] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int [n][n];
        visited = new boolean[n][n];
        
        for(int i=0;i<n;i++){
            String input = sc.next();
            for(int j=0;j<n;j++){
                map[i][j] = input.charAt(j)-'0';
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==1 && !visited[i][j]){
                    pq.offer(bfs(i,j));
                }
            }
        }
        Systs
    public static int bfs(int y , int x){
        int cnt = 1;
        visited[y][x] = true;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});

        while(!q.isEmpty()){
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];

            for(int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr>=0 && nr < n && nc>=0 && nc < n && map[nr][nc]==1 && !visited[nr][nc]){
                    q.offer(new int[]{nr , nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
            
        }
        return cnt;
    }
}
