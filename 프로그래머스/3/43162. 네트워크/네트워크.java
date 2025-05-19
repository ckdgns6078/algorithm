import java.util.*;

class Solution {
    
    static Set<Integer> set = new HashSet();
    static int[] parent;
    public int solution(int n, int[][] computers) {
        
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        
        for(int i=0 ; i<n;i++){
            for(int j=0;j<computers[i].length;j++){
                if( j !=i && computers[i][j]==1){
                    union(i , j);
                }
            }
        }
        
    for(int i = 0; i < parent.length; i++){
        set.add(find(i));
    }
        
        return set.size();
    }
    
    public static int find( int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public static void union(int x,  int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX!= rootY){
            parent[rootY] = parent[rootX];
        }
        
    }
    
    
    
}