import java.util.*;

class Solution {
    
    static int[] arr;
    static int[] sel = new int[5];
    static int answer=0;
    static int[] ans;
    static int[][] q;
    public int solution(int n, int[][] q, int[] ans) {
        
        arr = new int[n];
        this.q = q;
        this.ans = ans;
        for(int i=0;i<n;i++){
            arr[i] = i+1;
        }
        
        
        combination(0,0);
        return answer;
    }
    
    
    public static void combination(int idx , int k){
        if(sel.length == idx){
            if(check(sel)){
                answer++;
            }
            return;
        }
        
        if(arr.length == k){
            return;
        }
        sel[idx] = arr[k];
        combination(idx + 1, k + 1);
        combination(idx, k + 1);
        
    }
    
    
    public static boolean check(int[] sel){
        int resultCheck =0;
            
        for(int i=0;i<ans.length;i++){
            
            
            int[] temp = q[i];
            int resultTemp =0;
            for(int j=0;j<sel.length;j++){
                int cnt = sel[j];
                
                for(int k=0;k<temp.length;k++){
                    if(cnt == temp[k]){
                        resultTemp++;          
                    }
                }
            } 
            if(resultTemp != ans[i]){
                return false;
            }
        }       
        return true;
    }
    
}