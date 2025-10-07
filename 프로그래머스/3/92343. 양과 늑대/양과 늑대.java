import java.util.*;

class Solution {
    
    static int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        int size = info.length;
        List<Integer>[] node = new ArrayList[size];
        for(int i=0;i<size;i++){
            node[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            node[e[0]].add(e[1]);
        }
        
        List<Integer> startList = new ArrayList<>();
        startList.addAll(node[0]);
        dfs(info, node, 1, 0, startList);
        
        return maxSheep;
    }
    
    public void dfs(int[] info, List<Integer>[] node, int sheep, int wolf, List<Integer> available) {
        maxSheep = Math.max(maxSheep, sheep);
        
        for(int next : available){
            List<Integer> nextList = new ArrayList<>(available);
            nextList.remove(Integer.valueOf(next));
            nextList.addAll(node[next]);
            
            if(info[next] == 0){ // 양
                dfs(info, node, sheep + 1, wolf, nextList);
            }else{ // 늑대
                if(sheep > wolf + 1){ // 양이 더 많을 때만 이동
                    dfs(info, node, sheep, wolf + 1, nextList);
                }
            }
        }
    }
}
