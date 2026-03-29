import java.util.*;


class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        
        PriorityQueue<Integer> pq = new PriorityQueue();        
        
        for(int sco : scoville){
            pq.offer(sco);
        }
        
        while(pq.peek() < K){
            if(pq.size() < 2){
                return -1;
            }
            
            int first = pq.poll();
            int secont = pq.poll();
            pq.offer(first + secont * 2);
            answer++;
        }
        
        return answer;
    }
}