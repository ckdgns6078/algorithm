import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalTypes = gemTypes.size();

        Map<String, Integer> window = new HashMap<>();
        int start = 0, end = 0;
        int minLen = Integer.MAX_VALUE;
        int answerStart = 0, answerEnd = 0;

        while (true) {
            if (window.size() == totalTypes) {
                // 최소 길이 갱신 시도
                if (end - start < minLen) {
                    minLen = end - start;
                    answerStart = start + 1;  // 진열대는 1-based
                    answerEnd = end;
                }
                // 왼쪽 보석 제거
                String leftGem = gems[start];
                window.put(leftGem, window.get(leftGem) - 1);
                if (window.get(leftGem) == 0) {
                    window.remove(leftGem);
                }
                start++;
            } else if (end == gems.length) {
                break;
            } else {
                // 오른쪽 보석 추가
                window.put(gems[end], window.getOrDefault(gems[end], 0) + 1);
                end++;
            }
        }

        return new int[]{answerStart, answerEnd};
    }
}
