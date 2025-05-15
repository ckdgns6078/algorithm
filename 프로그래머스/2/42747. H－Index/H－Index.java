import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 오름차순 정렬
        int n = citations.length;
        int left = 0, right = n;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // mid 이상 인용된 논문 개수 세기
            int count = 0;
            for (int c : citations) {
                if (c >= mid) count++;
            }

            if (count >= mid) {
                answer = mid;       // h 조건 만족 → 더 큰 h도 가능할지 확인
                left = mid + 1;
            } else {
                right = mid - 1;    // h 조건 불만족 → 더 작은 h 탐색
            }
        }

        return answer;
    }
}
