import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -0;
        
        Map<Character, Character> map = new HashMap();
        map.put('}' , '{');
        map.put(')' , '(');
        map.put(']' , '[');
            
        int len = s.length();
        
        for(int i=0;i<len;i++){
            String rotated = s.substring(i) + s.substring(0,i);
            
            if(check(rotated , map)){
                answer++;
            }
        }
        return answer;
    }
    
    public static boolean check(String rotated , Map<Character, Character> map){
        Stack<Character> stack = new Stack();
        
        for(int i=0;i<rotated.length();i++){
            char c = rotated.charAt(i);
            
            if(c == '(' || c == '[' || c =='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char p = stack.pop();
                if(map.get(c) != p){
                    return false;
                }
                
            }
            
        }
        return stack.isEmpty();
    }
}