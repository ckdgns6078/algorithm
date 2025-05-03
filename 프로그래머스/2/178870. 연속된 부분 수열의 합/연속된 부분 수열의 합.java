class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        
        int maxIndex = sequence.length;
        int minLength = Integer.MAX_VALUE;
        int answerL = 0;
        int answerR = 0;

        while (left <= right && right < maxIndex) {
            if (sum > k) {
                sum -= sequence[left];
                left++;
            } else if (sum < k) {
                right++;
                if (right < maxIndex) {
                    sum += sequence[right];
                }
            } else {
                // sum == k인 경우 정답 후보 저장
                if ((right - left + 1) < minLength) {
                    minLength = right - left + 1;
                    answerL = left;
                    answerR = right;
                }

                right++;
                if (right < maxIndex) {
                    sum += sequence[right];
                }
            }
        }

        answer = new int[]{answerL, answerR};
        return answer;
    }
}
