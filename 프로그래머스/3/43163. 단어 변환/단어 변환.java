import java.util.*;

public class Solution {
    static Set<String> wordSet = new HashSet<>();
    static Set<String> visited = new HashSet<>();
    static int answer = Integer.MAX_VALUE;
    static String target;

    public int solution(String begin, String tgt, String[] words) {
        target = tgt;
        wordSet.addAll(Arrays.asList(words));
        
        // target이 words에 없다면 변환 불가능
        if (!wordSet.contains(target)) return 0;

        visited.add(begin);
        dfs(begin, 0);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public void dfs(String current, int cnt) {
        if (current.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }

        for (int i = 0; i < current.length(); i++) {
            char[] chars = current.toCharArray();

            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (chars[i] == ch) continue;

                chars[i] = ch;
                String next = new String(chars);

                if (wordSet.contains(next) && !visited.contains(next)) {
                    visited.add(next);
                    dfs(next, cnt + 1);
                    visited.remove(next); // 백트래킹
                }
            }
        }
    }
}
