import java.util.*;

class Solution {
    
    int[][] map;
    int n;
    int m;
    int x;
    int y;
    int r;
    int c;
    int k;
    String answer ="";
    boolean endCheck = false;
    
    int[] dr = {1, 0, 0, -1};  // d, l, r, u
    int[] dc = {0, -1, 1, 0};
    String[] com = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        this.n = n;
        this.m = m;
        this.x = x-1;
        this.y = y-1;
        this.r = r-1;
        this.c = c-1;
        this.k = k;
        this.map = new int[n][m];
        
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || (k - dist) % 2 != 0) return "impossible";

        
        dfs(this.x, this.y, 0, "");
        
        if (answer.equals("")){
            return "impossible";
        }
        
        return answer;
    }
    
    public void dfs(int rr, int cc , int cnt , String commend){
        //k값이 같고 도착지점일 경우 종료
        if(endCheck){
            return;
        }
        
        if(cnt == k && rr == r && cc == c){
            endCheck = true;
            answer = commend;
            return;
        }
        
        int dist = Math.abs(rr - r) + Math.abs(cc - c);
        if (cnt + dist > k){
            return;
        }
        
        
        for(int i=0;i<4;i++){
            int nr = rr + dr[i];
            int nc = cc + dc[i];
            String newCommend = com[i];
            
            if(nr>=0 && nr < n && nc >=0 && nc < m){
                dfs(nr,nc,cnt+1,commend+newCommend);
                if (endCheck) return;
            }   
        }
    }
   
}