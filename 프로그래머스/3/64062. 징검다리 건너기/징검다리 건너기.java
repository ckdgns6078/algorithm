import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < stones.length; i++) {
            min = Math.min(min , stones[i]);
            max = Math.max(max, stones[i]);
        }

        int left = min;
        int right = max;
        int answer =0;
        while (left < right) {
            int mid = (left + right) / 2;

            if (canCross(mid, stones, k)) {
                left = mid+1; 
                
            } else {
                right = mid;  
            }
        }

        return left; 
    }

    public boolean canCross(int mid, int[] stones, int k) {
        int temp = 0;
        for (int i : stones) {
            if (i - mid <= 0) temp++;
            else temp = 0;

            if (temp >= k) return false; // 연속 k개 이상 끊김 → 실패
        }
        return true;
    }
}
