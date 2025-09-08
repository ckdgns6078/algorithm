import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> used = new HashSet<>();
        used.add(words[0]); // 첫 단어 등록
        
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            
            // 조건 위반: 앞 단어의 끝글자 != 현재 단어의 첫글자
            if (prev.charAt(prev.length() - 1) != curr.charAt(0)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            
            // 조건 위반: 이미 나온 단어
            if (used.contains(curr)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            
            used.add(curr);
        }
        
        // 탈락자가 없는 경우
        return new int[]{0, 0};
    }
}
