import java.util.*;

public class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long r1Sq = (long) r1 * r1;
        long r2Sq = (long) r2 * r2;
        
        long side = 0;
        
        for (long x = 0; x <= r2; x++) {
            long y2 = (long) Math.sqrt(r2Sq - x * x);
            long y1 = 0;
            if (x <= r1) {
                y1 = (long) Math.sqrt(r1Sq - x * x);
                if (Math.sqrt(r1Sq - x * x) % 1 == 0) {
                    side++;
                }
            }
            answer += (y2 - y1) * 4;
        }
        
        answer += side * 4 - 4;
        
        return answer;
    }
}
