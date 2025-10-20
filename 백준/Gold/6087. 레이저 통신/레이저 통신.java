import java.util.*;

public class Main {

    static char[][] map;
    static int[][][] dist;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int[] dir = {0, 1, 2, 3};
    static int answer = Integer.MAX_VALUE;
    static int n;
    static int m;

    static class Node implements Comparable<Node> {
        int r, c, dir, cost;
        Node(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        map = new char[n][m];
        dist = new int[n][m][4];
        List<int[]> cList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                if (map[i][j] == 'C') cList.add(new int[]{i, j});
            }
        }

        int startR = cList.get(0)[0];
        int startC = cList.get(0)[1];
        int endR = cList.get(1)[0];
        int endC = cList.get(1)[1];

        bfs(startR, startC, endR, endC);
        System.out.println(answer);
    }

    public static void bfs(int startR, int startC, int endR, int endC) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int d = 0; d < 4; d++) {
            int nr = startR + dr[d];
            int nc = startC + dc[d];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
            if (map[nr][nc] == '*') continue;
            dist[nr][nc][d] = 0;
            pq.offer(new Node(nr, nc, d, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.r == endR && cur.c == endC) {
                answer = Math.min(answer, cur.cost);
                continue;
            }

            if (cur.cost > dist[cur.r][cur.c][cur.dir]) continue;

            for (int nd = 0; nd < 4; nd++) {
                int nr = cur.r + dr[nd];
                int nc = cur.c + dc[nd];
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (map[nr][nc] == '*') continue;

                int newCost = cur.cost + (cur.dir == nd ? 0 : 1);
                if (newCost < dist[nr][nc][nd]) {
                    dist[nr][nc][nd] = newCost;
                    pq.offer(new Node(nr, nc, nd, newCost));
                }
            }
        }
    }
}
