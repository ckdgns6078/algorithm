import java.util.*;

class Solution {
    public String solution(String s) {
        
        String[] arr = s.split(" ");
        int[] temp = new int[arr.length];
        
        int i =0;
        for(String cnt : arr){
            temp[i] = Integer.parseInt(cnt);
            i++;
        }
        
        Arrays.sort(temp);
        
        
        return temp[0] + " " + temp[temp.length-1];
    }
}