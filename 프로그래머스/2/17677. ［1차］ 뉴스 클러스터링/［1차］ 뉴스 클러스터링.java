import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Set<String> set = new HashSet<>();
        
        int size = sizeCheck(str1 , str2);
        Map<String,Integer> map1 = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();
        
        char c1 = str1.charAt(0);
        char c2 = str2.charAt(0);

        for(int i=1;i<size;i++){
            if(i<str1.length()){
                char c3 = str1.charAt(i);
                if(Character.isLetter(c1) && Character.isLetter(c3)){
                    String str = (""+ c1 + c3).toUpperCase();
                    set.add(str);
                    map1.put(str , map1.getOrDefault(str, 0) + 1);
                }
                c1 = c3;
            }

            if(i < str2.length()){
                char c4 = str2.charAt(i);
                if(Character.isLetter(c2) && Character.isLetter(c4)){
                    String str = (""+ c2 + c4).toUpperCase();
                    set.add(str);
                    map2.put(str , map2.getOrDefault(str, 0) + 1);
                }
                c2 = c4;
            }
        } // ✅ for(i) 루프 닫기

        // ✅ union, intersection은 루프 끝난 후 한 번만 계산
        int union = 0;
        int intersection = 0;
        for(String str : set){
            int u1 = check(str , map1);
            int u2 = check(str , map2);
            
            union += Math.max(u1 , u2);
            if(u1>0 && u2 > 0){
                intersection += Math.min(u1,u2);
            }
        }
        
        if(union==0){
            return 65536;
        }
    
        return (int)((intersection * 1.0 / union) * 65536); 
    }
    
    public int check(String str , Map<String,Integer> map){
        return map.getOrDefault(str, 0);
    }
    
    public int sizeCheck(String str1, String str2){
        return Math.max(str1.length(), str2.length());
    }
}
