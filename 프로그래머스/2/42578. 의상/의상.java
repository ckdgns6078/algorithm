import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 종류별 의상 개수 카운트
        Map<String, Integer> map = new HashMap<>();
        for (String[] c : clothes) {
            String type = c[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        // 경우의 수 계산
        int answer = 1;
        for (int count : map.values()) {
            answer *= (count + 1); // 입지 않는 경우 포함
        }

        return answer - 1; // 공집합 제외
    }
}
