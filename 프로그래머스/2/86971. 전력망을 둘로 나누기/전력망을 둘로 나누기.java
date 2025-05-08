import java.util.*;

class Solution {
    
    static List<Integer>[] list;
    static int n;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        this.n = n;
        
        list = new ArrayList[n+1];
        for(int i=1 ; i<n+1;i++){
            list[i] = new ArrayList();
        }
        
        for(int i=0;i<wires.length;i++){
            int start = wires[i][0];
            int end = wires[i][1];
            
            list[start].add(end);
            list[end].add(start);
        }
        
        
        for(int i=0;i<wires.length;i++){
            int start = wires[i][0];
            int end = wires[i][1];
            
            list[start].remove(Integer.valueOf(end));
            list[end].remove(Integer.valueOf(start));
            
            int cnt = bfs(start);
            int result = Math.abs(n - cnt - cnt);
            answer = Math.min(answer , result);
            
            list[start].add(end);
            list[end].add(start);
            
        }
        return answer;
    }
    
    public static int bfs(int start){
        Queue<Integer> q = new ArrayDeque();
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        q.offer(start);
        
        int cnt = 1;
        while(!q.isEmpty()){
            
            int p = q.poll();
            
            for(int idx : list[p]){
                if(visited[idx]){
                    continue;
                }
                
                visited[idx] = true;
                q.offer(idx);
                cnt++;
            }
            
            
        }
        
        return cnt;
        
    }
    
    
    
}