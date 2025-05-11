import java.util.*;

class Solution {
    
    static int[] dr = { -1 , 1 , 0 , 0};
    static int[] dc = { 0 , 0 , -1 , 1};
    static char[][][] map;
    
    
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        map = new char[places.length][5][5];
        
        for(int i=0;i<places.length;i++){
            for(int j=0;j<5;j++){
                String str = places[i][j];   
                for(int k=0;k<str.length();k++){
                    map[i][j][k] = str.charAt(k);
                }
            }
        }
        
        for(int i=0;i<map.length;i++){
            boolean check = true;
            L : for(int j=0;j<5;j++){
                    for(int k=0;k<5;k++){
                        if(map[i][j][k]=='P'){
                            check = bfs(i , j , k);
                        
                            if(!check){
                                break L;
                            }
                        }    
                    }
            }
            
            if(check){
                answer[i] = check==true ? 1 : 0;
            }   
        }
        return answer;
    }
    
    public static boolean bfs(int k , int r, int c){
        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[5][5];
        
        q.offer(new int[] { r , c , 0});
        visited[r][c] = true;
        
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            
            if(p[2] == 2){
                continue;
            }
            
            for(int i=0;i<4;i++){
                int nr= p[0] + dr[i];
                int nc= p[1] + dc[i];
                
                if(nr>=0 && nr<5 && nc>=0 && nc<5 && !visited[nr][nc] && map[k][nr][nc]!='X'){
                    
                    if(map[k][nr][nc]=='P'){
                        return false;
                    }
                    
                    q.offer(new int[] { nr , nc , p[2]+1});
                    visited[nr][nc] = true;
                    
                }
                
            }
            
            
            
        }
        
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}