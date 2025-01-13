import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 방향: 북(0), 동(1), 남(2), 서(3)
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int direction;
    static int[] cleaner = new int[2];
    static int[][] map;
    static int n, m;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        cleaner[0] = sc.nextInt();
        cleaner[1] = sc.nextInt();
        direction = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (true) {
            cleanCurrentPosition();

            if (countCleanableDirections(cleaner[0], cleaner[1]) == 0) { // 주변에 청소할 칸 없음
                if (!moveBack()) {
                    break; // 후진 불가능 -> 작동 종료
                }
            } else { // 주변에 청소할 칸 있음
                turnCleaner();
                foward();
            }
        }

        System.out.println(answer);
    }

    public static void cleanCurrentPosition() {
        if (map[cleaner[0]][cleaner[1]] == 0) {
            map[cleaner[0]][cleaner[1]] = 2; // 청소 완료 표시
            answer++;
        }
    }

    public static int countCleanableDirections(int r, int c) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean moveBack() {
        int backDirection = (direction + 2) % 4;
        int r = cleaner[0] + dr[backDirection];
        int c = cleaner[1] + dc[backDirection];

        if (r >= 0 && r < n && c >= 0 && c < m && map[r][c] != 1) {
            cleaner[0] = r;
            cleaner[1] = c;
            return true;
        }
        return false;
    }

    public static void turnCleaner() {
        direction = (direction + 3) % 4; // 반시계 방향 회전
    }

    public static void foward() {
        int r = cleaner[0] + dr[direction];
        int c = cleaner[1] + dc[direction];

        if (map[r][c] == 0) {
            cleaner[0] = r;
            cleaner[1] = c;
        }
    }
}
