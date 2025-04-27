import java.util.*;

class Solution {
    
    static int[] dr = { -1 , 1 , 0 , 0 };
    static int[] dc = { 0 , 0 , -1 , 1};
    static int[][] land;
    static int n;
    static int m;
    static Map<Integer, Integer> map;
    
    
    public int solution(int[][] land) {
        int answer = 0;
        this.land = land;
        n = land.length;
        m = land[0].length;
        map = new HashMap();
        int temp = 2;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(land[i][j] == 1){
                    int result = bfs( i,j , temp);
                    map.put(temp , result);
                    temp++;
                }
            }
        }
        
        for(int i=0;i<m;i++){
            answer = Math.max(answer , checkLine(i));
        }
        
        return answer;
    }
    
    public static int checkLine(int c){
        int result = 0;
        Set<Integer> visitedCheck = new HashSet();
        
        for(int i=0;i<n;i++){
            int idx = land[i][c];
            
            if(idx !=0){
                if(!visitedCheck.contains(idx)){
                    result += map.get(idx);
                    visitedCheck.add(idx);
                }
            }
        }
        return result;
    }
    
    
    
    public static int bfs(int r, int c , int temp){
        int result =1;
        Queue<int[]> q = new ArrayDeque();
        land[r][c] = temp;
        q.offer(new int[] { r,c});
        while(!q.isEmpty()){
            int[] p = q.poll();
            
            for(int i=0;i<4;i++){
                int nr = p[0] + dr[i];
                int nc = p[1] + dc[i];
                if(nr>=0 && nr< n && nc>=0 && nc< m && land[nr][nc]==1){
                    land[nr][nc] = temp;
                    q.offer(new int[] { nr , nc});
                    result++;
                }
            }
        }
        return result;
    }
    
}