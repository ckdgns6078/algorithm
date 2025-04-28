import java.util.*;

class Solution {
    
    static int[] dr = { -1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][] map;
    static boolean[][] visited;
    static int n;
    static int m;
    
    static int[] start;
    static int[] lever;
    static int[] end;
    
    public int solution(String[] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length();
        
        map = new char[n][m];
        for(int i=0;i<n;i++){
            String str = maps[i];
            for(int j=0;j<m;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='S'){
                    start = new int[3];
                    start[0] = i;
                    start[1] = j;
                }else if(map[i][j]=='L'){
                    lever = new int[3];
                    lever[0] = i;
                    lever[1] = j;
                }else if(map[i][j]=='E'){
                    end = new int[2];
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        
        int temp = bfs(start , lever);
        if(temp == -1){
            return -1;
        }
        lever[2] = temp;
        answer = bfs(lever , end);
        if(answer ==-1){
            return -1;
        }
        
        return answer;
    }
    
    
    public static int bfs(int[] start , int[] end){
        visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque();
        q.offer(new int[]{start[0] , start[1] , start[2]});
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];
            int pay = p[2];
            
            if(r==end[0] && c == end[1]){
                return pay;
            }
            
            
            for(int i=0;i<4;i++){
                int nr = r+dr[i];
                int nc = c+dc[i];
                
                if(nr<0 || nc<0 || nr>=n || nc>=m || map[nr][nc]=='X'){
                    continue;
                }
                if(visited[nr][nc]){
                    continue;
                }
                q.offer(new int[]{nr,nc,pay+1});
                visited[nr][nc] = true;
                 
                
            }
        }
        return -1;
    }
    
}