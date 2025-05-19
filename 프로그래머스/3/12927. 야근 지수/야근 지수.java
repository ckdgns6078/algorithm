import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int w : works){
            pq.offer(w);
        }
        
        int time = 0;
        while(time < n && !pq.isEmpty()){
            
            int cnt = pq.poll()-1;
            
            if(cnt>0){
                pq.offer(cnt);
            }
            time++;
        }
        
        while(!pq.isEmpty()){
            int cnt = pq.poll();
            answer+= cnt * cnt;
        }
        
        return answer;
    }
}