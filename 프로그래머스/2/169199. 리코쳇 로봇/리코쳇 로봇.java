import java.util.*;


class Solution {
    static int[] dr = { -1 , 1 , 0 , 0 };
    static int[] dc = { 0 , 0 , -1 , 1};
    static boolean[][] visited;
    static char[][] map;
    static int n;
    static int m;
    
    public int solution(String[] board) {
        int answer = 0;
        
        
        n = board.length;
        m = board[0].length();
        
        map = new char[n][m];
        visited = new boolean[n][m];
        
        int startR=0;
        int startC=0;
        int endR=0;
        int endC=0;
        for(int i=0;i<n;i++){
            String str  = board[i];
            for(int j=0;j<m;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    startR = i;
                    startC = j;
                }else if(map[i][j]=='G'){
                    endR = i;
                    endC = j;
                }
            }
        }
        
        answer = bfs(startR , startC , endR , endC);
        
        
        return answer;
    }
    
    public static int bfs(int sr , int sc , int er , int ec){
        Queue<int[]> q = new ArrayDeque();
        visited[sr][sc] = true;
        q.offer(new int[] { sr , sc , 0});
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            
            int r = p[0];
            int c = p[1];
            int pay = p[2];
            
            if(map[r][c] == 'G'){
                return pay;
            }
            
            
            for(int i=0;i<4;i++){
                int nr = r;
                int nc = c;
                while(true){
                    nr+= dr[i];
                    nc+= dc[i];
                    
                    
                    if(nr<0 || nc<0 || nr>=n || nc>=m || map[nr][nc]=='D'){
                        nr-= dr[i];
                        nc-= dc[i];
                        break;
                    }
                }
                if(!visited[nr][nc]){
                    q.offer(new int[]{ nr, nc , pay+1});
                    visited[nr][nc] = true;
                }
                
            }
            
            
        }
        
        return -1;
    }
    
    
}