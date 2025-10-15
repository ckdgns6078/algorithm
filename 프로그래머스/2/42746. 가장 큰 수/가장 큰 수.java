import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer ="";
        
        List<String> list = new ArrayList();
        for(int number : numbers){
            list.add(String.valueOf(number));
        }
        
        Collections.sort( list , (a,b) -> (b+a).compareTo(a+b));
        
        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i));
        }
        
        if (list.get(0).equals("0")) {
            return "0";
        }
        
        
        return sb.toString();
    }
}
