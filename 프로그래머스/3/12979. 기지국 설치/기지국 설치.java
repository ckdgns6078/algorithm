import java.util.*;

class Solution {
    
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        Queue<Integer> q = new ArrayDeque();
        
        int size = (w * 2) + 1;
        
        int start = 1;
        
        for(int st : stations){
            int left = st - w;
            int right = st + w;
            
            if(start < left){
                int cnt = left - start;
                answer += cnt / size;
                if(cnt% size !=0){
                    answer++;
                }
            }
            start = right +1;
        }
        
        if(start <= n){
            int cnt = n - start +1;
            answer += cnt / size;
            if(cnt% size !=0){
                answer++;
            }
        }

        
        return answer;
    }
}