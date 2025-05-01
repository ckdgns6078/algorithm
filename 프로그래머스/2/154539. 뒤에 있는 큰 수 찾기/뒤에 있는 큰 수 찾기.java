import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        int n = numbers.length-1;
        Stack<Integer> stack = new Stack();
        
        
        
        for(int i=n;i>=0 ;i--){
            while(!stack.isEmpty() && stack.peek() <= numbers[i]){
                stack.pop();
            }
            
            //초기 아무것도 없을 경우
            if(stack.isEmpty()){
                answer[i] = -1;
            }else{
                answer[i] = stack.peek();
            }
            
            stack.push(numbers[i]); 
        }
        
        
        
        
        
        return answer;
    }
}