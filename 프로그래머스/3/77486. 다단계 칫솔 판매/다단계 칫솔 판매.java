import java.util.*;

class Solution {
    
    
    static Map<String , String> parentMap = new HashMap();
    static Map<String , Integer> idxMap = new HashMap();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        int eSize = enroll.length;
        
        for(int i=0;i<eSize;i++){
            //en 자식
            //re 부모
            
            parentMap.put(enroll[i] , referral[i]);
            idxMap.put(enroll[i] , i);
        }
        
        
        for (int i = 0; i < amount.length; i++) {
            String current = seller[i];
            int profit = amount[i] * 100;

            while (!current.equals("-") && profit > 0) {
                int share = profit / 10;
                int myProfit = profit - share;

                
                answer[idxMap.get(current)] += myProfit;

                
                current = parentMap.get(current);
                profit = share;
            }

            
            if (!current.equals("-") && profit > 0) {
                answer[idxMap.get(current)] += profit;
            }
    }
        
        
        
        
        
        
        return answer;
    }
}