public class Solution {
    boolean solution(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else { // c == ')'
                count--;
                if (count < 0) {
                    return false; // 닫는 괄호가 더 많은 경우
                }
            }
        }

        return count == 0; // 여는 괄호와 닫는 괄호 수가 정확히 같은지 확인
    }
}
