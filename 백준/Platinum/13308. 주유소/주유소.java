import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[] oilBank;

    static List<Node>[] list;
    static long[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        oilBank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            oilBank[i] = sc.nextInt();
        }

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int di = sc.nextInt();

            list[start].add(new Node(end, di));
            list[end].add(new Node(start, di));
        }

        dist = new long[N + 1][2501];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        dijkstra(1);

        long answer = Long.MAX_VALUE;
        for (long cnt : dist[N]) {
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, oilBank[start]));
        dist[start][oilBank[start]] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.idx;
            long oilSum = node.oilSum;
            int min = node.min;

            if (oilSum > dist[now][min])
                continue;

            for (Node next : list[now]) {
                long newOilSum = oilSum + (long) min * next.dist;
                int newMin = Math.min(min, oilBank[next.idx]);
                if (newOilSum < dist[next.idx][newMin]) {
                    pq.offer(new Node(next.idx, newOilSum, newMin));
                    dist[next.idx][newMin] = newOilSum;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        long oilSum;
        int dist;
        int min;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public Node(int idx, long oilSum, int min) {
            this.idx = idx;
            this.oilSum = oilSum;
            this.min = min;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.oilSum, o.oilSum);
        }
    }
}
