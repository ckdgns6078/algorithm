import java.util.*;

class Solution {
    
    static int[] dr = { -1 , 1  , 0 , 0};
    static int[] dc = { 0 , 0 , -1 , 1};
    static char[][] map;
    static boolean[][] visited;
    static int n;
    static int m;
    
    public int[] solution(String[] maps) {
        int[] answer = {};

        ArrayList<Integer> resultList = new ArrayList();
        
        insertInput(maps);
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] !='X' && !visited[i][j]){
                    int result = bfs(i ,j);
                    resultList.add(result);
                }
            }
        }
        answer = new int[resultList.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = resultList.get(i);
        }
        Arrays.sort(answer);
        if(answer.length==0){
           return new int[]{-1};
        }
        return answer;
    }
    
    public static int bfs(int sr , int sc){
        int result =map[sr][sc]-'0';
        Queue<int[]> q = new ArrayDeque();
        visited[sr][sc] = true;
        q.offer(new int[]{sr,sc});
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];
            
            for(int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr<0 || nc<0 || nr>=n || nc>=m){
                    continue;
                }
                if(visited[nr][nc] || map[nr][nc]=='X'){
                    continue;
                }
                
                q.offer(new int[]{nr,nc});
                visited[nr][nc]= true;
                result+=map[nr][nc]-'0';
                
                
            }
        }
        return result;
        
    }
    
    
    public static void insertInput(String[] maps){
        n = maps.length;
        m = maps[0].length();
        
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            String str = maps[i];
            for(int j=0;j<m;j++){
                map[i][j] = str.charAt(j);
            }
        }
    }
    
}