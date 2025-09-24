import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left =0;
        long right = 1000000000 * 1000000000L;
        
        while(left < right){
            long mid = (left + right) / 2;
            
            if(check(times, mid, n)){
                right = mid;
            }else{
                left = mid+1;
            }
            
        }
        
        return left;
    }
    
    public boolean check(int[] times, long mid , int n){
        long sum = 0;
        
        for(int i=0;i<times.length;i++){
            sum += mid / times[i];
        }
        
        if(sum >= n){
            return true;
        }
        return false;
    }
}