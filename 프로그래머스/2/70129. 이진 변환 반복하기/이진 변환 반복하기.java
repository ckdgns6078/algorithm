import java.util.*;


class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int idx =0;
        int cnt =0;
        
        while(!s.equals("1")){
            
            int nsize = s.length();    
            s = s.replace("0" , "");
            int csize = s.length();
            cnt+= nsize-csize;
            s = Integer.toBinaryString(csize);
            idx++;
        }
        

        answer[0] = idx;
        answer[1] = cnt;
        
        
        return answer;
    }
}