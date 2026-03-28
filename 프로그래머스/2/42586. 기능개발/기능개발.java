import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> q = new ArrayDeque();
        
        for(int i=0;i<progresses.length;i++){
            int pro = 100 - progresses[i];
            
            int day = pro % speeds[i] == 0 ? pro / speeds[i] : pro / speeds[i] +1;
            
            q.offer(day);
            
        }
        
        List<Integer> result = new ArrayList();
        while(!q.isEmpty()){
            
            int p = q.poll();
            int cnt = 1;
            
            while(!q.isEmpty() && q.peek() <= p){
                q.poll();
                cnt++;
            }
            result.add(cnt);
        }
        
        int[] answer = new int[result.size()];
        
        for(int i=0;i<result.size();i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}

//큐를 써서 풀이할 필요도 없는거같은데 그냥