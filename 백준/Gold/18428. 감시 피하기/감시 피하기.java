import java.util.*;

public class Main{
    static int N;
    static char[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static boolean found = false;

    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.next().charAt(0);
                if (map[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                }
            }
        }

        backtracking(0, 0, 0);

        System.out.println(found ? "YES" : "NO");
    }

    static void backtracking(int r, int c, int cnt) {
        if (found) return;

        if (cnt == 3) {
            if (!checkSight()) {
                found = true;
            }
            return;
        }

        for (int i = r; i < N; i++) {
            for (int j = (i == r ? c : 0); j < N; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    if (j + 1 < N) {
                        backtracking(i, j + 1, cnt + 1);
                    } else {
                        backtracking(i + 1, 0, cnt + 1);
                    }
                    map[i][j] = 'X';
                }
            }
        }
    }

    static boolean checkSight() {
        for (int[] t : teachers) {
            for (int d = 0; d < 4; d++) {
                if (watch(t[0], t[1], d)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean watch(int r, int c, int dir) {
        while (true) {
            r += dr[dir];
            c += dc[dir];

            if (r < 0 || r >= N || c < 0 || c >= N) return false;
            if (map[r][c] == 'O') return false;
            if (map[r][c] == 'S') return true;
        }
    }
}
