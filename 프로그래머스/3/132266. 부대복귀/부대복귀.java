import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 인접 리스트 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        // 2. 최단 거리를 저장할 배열 (초기값 -1)
        int[] dists = new int[n + 1];
        Arrays.fill(dists, -1);

        // 3. destination에서 시작하는 BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dists[destination] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (dists[next] == -1) { // 아직 방문하지 않은 곳이라면
                    dists[next] = dists[current] + 1;
                    queue.add(next);
                }
            }
        }

        // 4. sources 순서대로 결과값 정리
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dists[sources[i]];
        }

        return answer;
    }
}