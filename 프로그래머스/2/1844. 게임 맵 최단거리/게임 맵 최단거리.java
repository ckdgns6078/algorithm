import java.util.*;

class Solution {
    static int[] dr = {-1 , 1 , 0 , 0};
    static int[] dc = { 0, 0, -1 , 1};
    static boolean[][] visited;
    public int solution(int[][] maps) {
        int answer = -1;
        int sizeR = maps.length;
        int sizeC = maps[0].length;
        
        visited = new boolean[sizeR][sizeC];
        
        visited[0][0] = true;
        
        Queue<int[]> q = new ArrayDeque();
        
        q.offer(new int[]{0,0,1});
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];
            int cnt = p[2];
            
            if(r == sizeR-1 && c == sizeC-1){
                return cnt;
            }
            
            for(int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nc < 0 || nr >=sizeR || nc >=sizeC || maps[nr][nc] == 0 || visited[nr][nc]){
                    continue;
                }
                
                q.offer(new int[]{nr,nc,cnt+1});
                visited[nr][nc] = true;
                
            }
            
        }
        
        
        
        return answer;
    }
}