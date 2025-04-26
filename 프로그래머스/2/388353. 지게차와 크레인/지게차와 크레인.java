import java.util.*;

class Solution {
    static int n;
    static int m;
    static char[][] map;
    static Set<Character> set = new HashSet();
    static int[] dr = {-1 , 1 , 0 , 0 };
    static int[] dc = {0 , 0, -1 , 1};
    
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        n = storage.length;
        m = storage[0].length();
        
        map = new char[n][m];
        
        for(int i=0;i<storage.length;i++){
            String str = storage[i];
            for(int j=0;j<str.length() ; j++){
                map[i][j] = str.charAt(j);
            }
        }    
     
        for(int i=0;i<requests.length;i++){
            String request = requests[i];
            int size = request.length();
            
            for(int j=0;j<request.length();j++){
                char c = request.charAt(j);
                if(size ==1){ //지게차
                    List<int[]> deleteList = forkLift(c);
                    for( int[] delete : deleteList){
                        map[delete[0]][delete[1]] = '0';
                    }
                }else{  //크레인
                    if(!set.contains(c)){
                        crain(c);
                        set.add(c);
                    }
                              
                }
            }
            
        }   
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] !='0'){
                    answer++;
                }
            }
        }
        return answer;
    }
    
    
    
    public static List<int[]> forkLift(char c){
        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[n][m];
        List<int[]> resultList = new ArrayList();
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 || i ==n-1 || j==0 || j==m-1){
                    if(map[i][j] == c || map[i][j]=='0'){
                        q.offer(new int[] { i , j});
                        visited[i][j] = true;    
                    }
                }
            }
        }
        while(!q.isEmpty()){ 
            int[] p = q.poll();
                    
            if(map[p[0]][p[1]]==c){
                resultList.add(new int[] { p[0],p[1]});
                continue;
            }
            
            
            for(int i=0;i<4;i++){
                int nr = p[0] + dr[i];
                int nc = p[1] + dc[i];
                
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc]){
                    if(map[nr][nc]==c || map[nr][nc]=='0'){
                        q.offer(new int[]{nr,nc});
                        visited[nr][nc] = true;    
                    }
                }
            }
        }
        
        return resultList;
        
    }
    
    public static void crain(char c){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == c){
                    map[i][j] = '0';
                }
            }
        }
    }
    
    
}