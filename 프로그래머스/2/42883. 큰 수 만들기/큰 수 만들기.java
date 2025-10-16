import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";

        //deque를 통해서 풀이를 해야한다
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i=0;i<number.length();i++){
            int num = number.charAt(i)-'0';
            
            while(!deque.isEmpty() && k > 0 && deque.peekLast() < num){
                deque.removeLast();
                k--;
            }
            deque.addLast(num);
        }
        
        if(k>0){
            for(int i=0;i<k;i++){
                deque.removeLast();
            }
        }
        StringBuilder sb = new StringBuilder();
        
        for(int n : deque){
            sb.append(n);    
        }
        
        return sb.toString();
    }
}