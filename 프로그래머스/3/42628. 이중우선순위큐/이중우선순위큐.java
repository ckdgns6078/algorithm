import java.util.*;

class Solution {
    static TreeMap<Integer , Integer> map = new TreeMap();
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        for(String str : operations){
            String[] arr = str.split(" ");
            
            String commend = arr[0];
            int cnt = Integer.parseInt(arr[1]);
            
            if(commend.equals("I")){            //신규 값 넣기
                if(map.containsKey(cnt)){
                    map.put(cnt , map.get(cnt) +1);
                }else{
                    map.put(cnt, 1);
                }
                
                
            }else{
                if(!map.isEmpty()){
                    int key = cnt == -1 ? map.firstKey() : map.lastKey();
                    int count = map.get(key)-1;
                    if(count <=0){
                        map.remove(key);
                    }
                }   
            }    
        }
        
        if(map.isEmpty()){
            return answer;
        }else{
            return new int[]{map.lastKey() , map.firstKey()};    
        }
        
        
    }
}