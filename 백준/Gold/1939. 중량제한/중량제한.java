import java.util.*;

public class Main {
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        int n = sc.nextInt(); // 섬 개수
        int m = sc.nextInt(); // 다리 개수

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        int start = sc.nextInt(); // 공장 1 위치
        int end = sc.nextInt();   // 공장 2 위치

        // 다익스트라로 최대 중량 계산
        int result = dijkstra(graph, n, start, end);
        System.out.println(result);
    }

    public static int dijkstra(List<List<Edge>> graph, int n, int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.weight, a.weight));
        boolean[] visited = new boolean[n + 1];
        pq.offer(new Edge(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.to;
            int weight = current.weight;

            if (visited[node]) continue;
            visited[node] = true;

            if (node == end) return weight;

            for (Edge next : graph.get(node)) {
                if (!visited[next.to]) {
                    pq.offer(new Edge(next.to, Math.min(weight, next.weight)));
                }
            }
        }

        return 0; // 경로가 항상 존재한다고 했으므로 여기에는 도달하지 않음
    }
}
