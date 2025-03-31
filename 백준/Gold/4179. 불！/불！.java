import java.util.*;

public class Main {

    static char[][] maze;
    static int R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> fireQueue = new ArrayDeque<>();
    static Queue<int[]> jihunQueue = new ArrayDeque<>();
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        maze = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                maze[i][j] = str.charAt(j);
                if (maze[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j});
                } else if (maze[i][j] == 'J') {
                    jihunQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int time = 0;

        while (!jihunQueue.isEmpty()) {
            time++;

            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                int[] f = fireQueue.poll();
                int fr = f[0];
                int fc = f[1];

                for (int d = 0; d < 4; d++) {
                    int nfr = fr + dr[d];
                    int nfc = fc + dc[d];

                    if (nfr >= 0 && nfr < R && nfc >= 0 && nfc < C) {
                        if (maze[nfr][nfc] == '.' || maze[nfr][nfc] == 'J') {
                            maze[nfr][nfc] = 'F';
                            fireQueue.offer(new int[]{nfr, nfc});
                        }
                    }
                }
            }

            int jihunSize = jihunQueue.size();
            for (int i = 0; i < jihunSize; i++) {
                int[] j = jihunQueue.poll();
                int jr = j[0];
                int jc = j[1];

                for (int d = 0; d < 4; d++) {
                    int njr = jr + dr[d];
                    int njc = jc + dc[d];

                    if (njr < 0 || njr >= R || njc < 0 || njc >= C) {
                        System.out.println(time);
                        return;
                    }

                    if (maze[njr][njc] == '.' && !visited[njr][njc]) {
                        visited[njr][njc] = true;
                        jihunQueue.offer(new int[]{njr, njc});
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
