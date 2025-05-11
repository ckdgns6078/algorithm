public class Solution {
    private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    private int count = 0;
    private int answer = 0;
    private boolean found = false;

    public int solution(String word) {
        dfs("", word);
        return answer;
    }

    private void dfs(String current, String target) {
        if (found) return;

        if (!current.isEmpty()) {
            count++;
            if (current.equals(target)) {
                answer = count;
                found = true;
                return;
            }
        }

        if (current.length() == 5) return;

        for (char c : VOWELS) {
            dfs(current + c, target);
        }
    }
}
