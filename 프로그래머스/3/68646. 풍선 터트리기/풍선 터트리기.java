import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        int n = a.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min = Math.min(min , a[i]);
            left[i] = min;
        }
        
        min = Integer.MAX_VALUE;
        for(int i= n-1 ; i>=0 ; i--){
            min = Math.min(min , a[i]);
            right[i] = min;
        }
        
        for(int i=0;i<n;i++){
            int temp = a[i];
            if(temp <=left[i] || temp <=right[i]){
                answer++;
            }
            
            
        }
        
        
        return answer;
    }
}