import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int size = n+1;
        
        List<Integer>[] winList = new ArrayList[size];
        List<Integer>[] loseList = new ArrayList[size];
        
        for(int i=1;i<size;i++){
            winList[i] = new ArrayList();
            loseList[i] = new ArrayList();
        }
        
        for(int i=0;i<results.length;i++){
            int win = results[i][0];
            int lose = results[i][1];
            
            winList[win].add(lose);
            loseList[lose].add(win);
        }
        
        for(int i=1;i<size;i++){
            int sum = bfs(i , winList, size) + bfs(i ,loseList, size);
            if(sum==n-1){
                answer++;
            }
        }
        
        return answer;
    }
    
    public int bfs(int start , List<Integer>[] list, int size){
        boolean[] visited = new boolean[size];
        int result = 0;
        Queue<Integer>q = new ArrayDeque();
        visited[start] = true;
        q.offer(start);
        
        while(!q.isEmpty()){
            
            int node = q.poll();
            
            for(int next : list[node]){
                if(!visited[next]){
                    q.offer(next);
                    result++;
                    visited[next] = true;
                }
            }
        }
        return result;
    }
    
}