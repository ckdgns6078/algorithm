import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visited = new boolean[100001];
    static int[] prev = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        bfs();
    }

    public static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[N] = true;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == M) {
                List<Integer> path = new ArrayList<>();
                for (int i = M; i != -1; i = prev[i]) {
                    path.add(i);
                }
                Collections.reverse(path);

                // 출력
                System.out.println(path.size() - 1);
                for (int pos : path) {
                    System.out.print(pos + " ");
                }
                return;
            }

            int[] nexts = {now - 1, now + 1, now * 2};
            for (int next : nexts) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    prev[next] = now;
                    q.offer(next);
                }
            }
        }
    }
}
