import java.util.*;

class Solution {
    
    static String[] arr = {"aya", "ye", "woo", "ma"};
    static int answer;
    
    public int solution(String[] babbling) {
        answer = 0;
        
        for (int i = 0; i < babbling.length; i++) {
            checkStr(babbling[i], new boolean[4]);
        }
        
        return answer;
    }
    
    public static void checkStr(String str, boolean[] visited) {
        if (str.equals("")) {
            answer++;
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            if (!visited[i] && str.startsWith(arr[i])) {
                boolean[] nextVisited = visited.clone();
                nextVisited[i] = true;
                checkStr(str.substring(arr[i].length()), nextVisited);
            }
        }
    }
}
