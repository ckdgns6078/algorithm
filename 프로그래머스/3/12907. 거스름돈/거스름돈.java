class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int c : money){
            for(int i=c ;i<n+1;i++){
                dp[i] += dp[i-c];
                dp[i] %= 1000000007;
            }
            
            
        }
        
        return dp[n];
    }
}