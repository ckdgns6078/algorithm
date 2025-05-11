import java.util.*;

class Solution {
    
    static Map<String , String> map = new HashMap();
    
    
    public String[] solution(String[] record) {
        List<String> list = new ArrayList();
        
        
        for(String str  : record){
            String[] cut = str.split(" ");
            String type = cut[0];
            String id = cut[1];
            String nickName;
            String ans = "";
                 
            if("Leave".equals(cut[0])){
                ans = id+"님이 나갔습니다.";
                
            }else if("Enter".equals(cut[0])){
                nickName = cut[2];
                map.put(id , nickName);    
                
                ans += id+"님이 들어왔습니다.";
            }else{
                nickName = cut[2];
                map.put(id , nickName);
            }
            
            if(!ans.equals("")){
                list.add(ans);
            }
            
        }
        
        int size = list.size();
        String[] answer = new String[size];        
        int idx =0;
        for(int i =0; i<list.size() ;i++){
            String str = list.get(i);
            
            String [] cut = str.split("님이");
            String id = cut[0];
            answer[i] = str.replace(id , map.get(id));
            idx++;
        }
        
        
        return answer;
    }

}