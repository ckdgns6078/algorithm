import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            list.add(B[i]);
        }
        Collections.sort(list);

        for (int i = 0; i < A.length; i++) {
            int cnt = A[i];

            int left = 0;
            int right = list.size() - 1;
            int idx = -1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (list.get(mid) > cnt) {
                    idx = mid; // 후보 저장
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (idx != -1) {
                answer++;
                list.remove(idx); // O(N) 시간, 최악 시 비효율적
            }
        }

        return answer;
    }
}
