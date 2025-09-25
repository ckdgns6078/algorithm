import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int answer = 0;


        for (int m = 0; m < n - 1; m++) {
            int left = m;
            int right = m + 1;
            int sumL = cookie[left];
            int sumR = cookie[right];


            while (0 <= left && right < n) {
                if (sumL == sumR) {
                    answer = Math.max(answer, sumL);
                }
                if (sumL <= sumR && left > 0) { 
                    left--;
                    sumL += cookie[left];
                } else if (sumL > sumR && right < n - 1) {
                    right++;
                    sumR += cookie[right];
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}
