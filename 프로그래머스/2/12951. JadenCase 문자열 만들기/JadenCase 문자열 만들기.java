public class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String lower = s.toLowerCase();  // 모든 문자를 소문자로 변환
        boolean isStart = true;  // 단어의 시작 여부

        for (char c : lower.toCharArray()) {
            if (isStart && Character.isLetter(c)) {
                answer.append(Character.toUpperCase(c));  // 단어 시작이면 대문자로
            } else {
                answer.append(c);  // 나머지는 그대로 추가
            }
            isStart = (c == ' ');  // 공백이면 다음 문자가 단어의 시작
        }

        return answer.toString();
    }
}
