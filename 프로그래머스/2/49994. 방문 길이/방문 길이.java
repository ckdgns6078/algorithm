import java.util.*;

class Solution {
    
    
    
    // U : 0 D : 1 L-> 2 R ->3
    public int solution(String dirs) {
        int answer =0;
        Set<String> set = new HashSet();
        
        int sr = 5;
        int sc = 5;
        
        for(int i=0;i<dirs.length() ; i++){
            char c = dirs.charAt(i);
            
            int nr = sr;
            int nc = sc;
            
            if(c == 'U'){
                nr -= 1;
            }else if(c == 'D'){
                nr +=1;
            }else if(c == 'L'){
                nc -=1;
            }else{
                nc +=1;
            }
            
            if(nr < 0 || nc < 0 || nr >=11 || nc >= 11){
                continue;
            }
            
            String dir1 = "" + sr + sc + nr + nc ;
            String dir2 = "" + nr + nc + sr + sc ;
            
            if(!set.contains(dir1)){
                set.add(dir1);
                set.add(dir2);
                answer++;
            }
            sr = nr;
            sc = nc;
        }
        return answer;
    }
}