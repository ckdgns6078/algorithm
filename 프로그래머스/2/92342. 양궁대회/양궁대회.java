import java.util.*;

public class Solution {
    static int[] best = {-1};
    static int maxGap = 0;

    public static int[] solution(int n, int[] info) {
        dfs(0, n, new int[11], info);
        return best;
    }

    private static void dfs(int idx, int arrowsLeft, int[] ryan, int[] apeach) {
        if (idx == 11) {
            if (arrowsLeft > 0) {
                ryan[10] += arrowsLeft; // 남은 화살 전부 0점에 사용
            }

            int ryanScore = 0, apeachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (ryan[i] > apeach[i]) ryanScore += 10 - i;
                else if (apeach[i] > 0) apeachScore += 10 - i;
            }

            int gap = ryanScore - apeachScore;
            if (gap > 0) {
                if (gap > maxGap || (gap == maxGap && isBetter(ryan))) {
                    maxGap = gap;
                    best = ryan.clone();
                }
            }

            if (arrowsLeft > 0) {
                ryan[10] -= arrowsLeft; // 원복
            }
            return;
        }


        // 라이언이 이 점수를 가져가는 경우
        if (arrowsLeft > apeach[idx]) {
            ryan[idx] = apeach[idx] + 1;
            dfs(idx + 1, arrowsLeft - ryan[idx], ryan, apeach);
            ryan[idx] = 0;
        }

        // 라이언이 이 점수를 포기하는 경우
        dfs(idx + 1, arrowsLeft, ryan, apeach);
    }

    private static boolean isBetter(int[] newPlan) {
        for (int i = 10; i >= 0; i--) { // 점수 낮은 것부터 비교
            if (newPlan[i] > best[i]) return true;
            else if (newPlan[i] < best[i]) return false;
        }
        return false;
    }
}
