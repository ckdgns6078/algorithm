import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[y + 1];

        queue.offer(x);
        visited[x] = true;

        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                if (curr == y) return depth;

                int[] next = {curr + n, curr * 2, curr * 3};

                for (int nx : next) {
                    if (nx <= y && !visited[nx]) {
                        visited[nx] = true;
                        queue.offer(nx);
                    }
                }
            }

            depth++;
        }

        return -1;
    }
}
