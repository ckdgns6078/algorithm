import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : costs) {
            int from = edge[0], to = edge[1], cost = edge[2];
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[n];

        int totalCost = 0;
        int visitedCount = 0;

        
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty() && visitedCount < n) {
            int[] current = pq.poll();
            int island = current[0];
            int cost = current[1];

            if (visited[island]) continue;

            visited[island] = true;
            totalCost += cost;
            visitedCount++;

            for (int[] next : graph.get(island)) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], next[1]});
                }
            }
        }

        return totalCost;
    }
}
