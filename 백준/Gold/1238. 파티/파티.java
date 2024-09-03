import java.util.*;

class Vertex implements Comparable<Vertex> {
    int vertex, weight;

    Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public int compareTo(Vertex o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {
    static int N, M, X;
    static ArrayList<Vertex>[] graph, reverseGraph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Vertex(v, w));
            reverseGraph[v].add(new Vertex(u, w)); // 역방향 그래프
        }

        int[] toX = dijkstra(graph, X);
        int[] fromX = dijkstra(reverseGraph, X);

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, toX[i] + fromX[i]);
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(ArrayList<Vertex>[] graph, int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            if (dist[current.vertex] < current.weight) continue;

            for (Vertex neighbor : graph[current.vertex]) {
                int newDist = dist[current.vertex] + neighbor.weight;
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.add(new Vertex(neighbor.vertex, newDist));
                }
            }
        }

        return dist;
    }
}