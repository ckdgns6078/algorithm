import java.util.*;

public class Main {

    static int N;
    static int M;
    static int K;
    static List<Node>[] list;
    static long[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList[N + 1];
        dist = new long[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            list[start].add(new Node(end, cost, 0));
            list[end].add(new Node(start, cost, 0));
        }

        // 시작지점은 1이고 도착지점은 무조건 N이다.
        run(1);

        long answer = Long.MAX_VALUE;
        for (long cnt : dist[N]) {
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);
    }

    public static void run(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0L, 0));
        dist[startNode][0] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.idx;
            long cost = node.cost;
            int check = node.check;

            if (dist[now][check] < cost) continue;

            for (Node n : list[now]) {
                int nextNode = n.idx;
                long nextCost = n.cost;

                if (cost + nextCost < dist[nextNode][check]) {
                    long newCost = cost + nextCost;
                    pq.offer(new Node(nextNode, newCost, check));
                    dist[nextNode][check] = newCost;
                }

                if (check < K && cost < dist[nextNode][check + 1]) {
                    long newCost = cost;
                    pq.offer(new Node(nextNode, newCost, check + 1));
                    dist[nextNode][check + 1] = newCost;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        long cost;
        int check;

        Node(int idx, long cost, int check) {
            this.idx = idx;
            this.cost = cost;
            this.check = check;
        }

        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
