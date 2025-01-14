import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<int[]>[][] switches;
    static boolean[][] light, visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // 스위치 초기화
        switches = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        // 스위치 정보 입력
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            switches[x][y].add(new int[] { a, b });
        }

        // 불 켜기와 방문 기록 초기화
        light = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        // 시작 방은 항상 불이 켜져 있음
        light[1][1] = true;

        // BFS 탐색
        bfs();

        // 결과 계산: 불이 켜진 방의 개수
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (light[i][j]) count++;
            }
        }

        System.out.println(count);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 1, 1 });
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            // 현재 방에서 켤 수 있는 방의 불 켜기
            for (int[] room : switches[r][c]) {
                int nr = room[0];
                int nc = room[1];
                if (!light[nr][nc]) {
                    light[nr][nc] = true;

                    // 새로 켜진 방이 방문 가능한 경우 큐에 추가
                    if (isAdjacentToVisited(nr, nc)) {
                        queue.offer(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }

            // 4방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc] && light[nr][nc]) {
                    queue.offer(new int[] { nr, nc });
                    visited[nr][nc] = true;
                }
            }
        }
    }

    // 새로 켜진 방이 방문 가능한지 확인
    static boolean isAdjacentToVisited(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr > 0 && nr <= N && nc > 0 && nc <= N && visited[nr][nc]) {
                return true;
            }
        }
        return false;
    }
}