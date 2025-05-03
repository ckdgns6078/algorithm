import java.util.*;

class Solution {
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] picks;
    static String[] minerals;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        this.picks = picks;
        this.minerals = minerals;

        createPq();

        int idx = 0;
        while (!pq.isEmpty() && idx < 3) {

            
            while (idx < 3 && picks[idx] == 0) {
                idx++;
            }

            if (idx == 3) break;

            Node n = pq.poll();
            int start = n.start;
            int end = n.end;

            for (int i = start; i < end; i++) {
                String m = minerals[i];

                if (idx == 0) {
                    answer += 1;
                } else if (idx == 1) {
                    if ("diamond".equals(m)) answer += 5;
                    else answer += 1;
                } else {
                    if ("diamond".equals(m)) answer += 25;
                    else if ("iron".equals(m)) answer += 5;
                    else answer += 1;
                }
            }

            picks[idx]--;
        }

        return answer;
    }

public static void createPq() {
    int totalPicks = picks[0] + picks[1] + picks[2];
    int blockCount = Math.min((minerals.length + 4) / 5, totalPicks);

    for (int i = 0; i < blockCount; i++) {
        int start = i * 5;
        int end = Math.min(start + 5, minerals.length);
        int sum = 0;

        for (int j = start; j < end; j++) {
            if ("diamond".equals(minerals[j])) {
                sum += 25;
            } else if ("iron".equals(minerals[j])) {
                sum += 5;
            } else {
                sum += 1;
            }
        }

        pq.offer(new Node(start, end, sum));
    }
}


    static class Node implements Comparable<Node> {
        int start;
        int end;
        int sum;

        public Node(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.sum, this.sum); // 내림차순
        }
    }
}
