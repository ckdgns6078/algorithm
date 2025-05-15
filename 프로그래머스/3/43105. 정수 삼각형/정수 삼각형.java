import java.util.*;

class Solution {
    
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int[][] dp = new int[size][size];
        
        for(int i=0;i<size;i++){
            dp[size-1][i] = triangle[size-1][i];
        }
        
        for(int i= size-2 ;i>=0 ; i--){
            for(int j=0;j<triangle[i].length;j++){
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j] , dp[i+1][j+1]);
            }

        }

        return dp[0][0];
    }
}