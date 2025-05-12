import java.util.*;

class Solution {
    static int[] dr = {1 , 0 , -1};
    static int[] dc = {0 , 1 , -1};
    static boolean[][] visited;
    static int[][] map;
    static ArrayList<Integer> list = new ArrayList();
    
    public int[] solution(int n) {
        if(n == 1){
            return new int[]{1};
        }
        
        
        map = new int[n][n];
        visited = new boolean[n][n];
        
        
        map[0][0] = 1;
        visited[0][0] = true;
        int sr = 0;
        int sc = 0;
        int dir = 0;
        int cnt = 2;
        list.add(1);
        
        while(cnt <= n * (n + 1) / 2){
            
            int nr = sr + dr[dir];
            int nc = sc + dc[dir];

            if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]){
                dir++;
                if(dir == 3){
                    dir = 0;
                }
                continue;
            }
            
            visited[nr][nc] = true;
            map[nr][nc] = cnt;
            sr = nr;
            sc = nc;
            list.add(cnt);
            cnt++;
        }

        
        
        int[] answer = new int[n * (n + 1)/2];
        
        int idx =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==0){
                    break;
                }
                answer[idx] = map[i][j];
                idx++;
            }
        }
        
        return answer;
    }
    

}