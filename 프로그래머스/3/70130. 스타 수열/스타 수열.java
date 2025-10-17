import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : a) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for (int num : map.keySet()) {
            if (map.get(num) <= answer / 2) continue; // pruning

            int check = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] != a[i+1] && (a[i] == num || a[i+1] == num)) {
                    check += 2;
                    i++;
                }
            }
            answer = Math.max(answer, check);
        }
        
        return answer;
    }
}
