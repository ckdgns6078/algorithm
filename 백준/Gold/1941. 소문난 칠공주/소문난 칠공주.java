import java.util.*;

public class Main {
    static int answer = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static int[] sel = new int[7];
    static Set<String> visitedComb = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String input = sc.next();
            for (int j = 0; j < 5; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        combination(0, 0);
        System.out.println(answer);
    }

    public static void combination(int start, int depth) {
        if (depth == 7) {
            if (check(sel) && bfs(sel)) {
                int[] temp = Arrays.copyOf(sel, 7);
                Arrays.sort(temp);
                StringBuilder sb = new StringBuilder();
                for (int t : temp) {
                    sb.append(t).append(",");
                }
                String key = sb.toString();
                if (!visitedComb.contains(key)) {
                    visitedComb.add(key);
                    answer++;
                }
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            sel[depth] = i;
            combination(i + 1, depth + 1);
        }
    }

    public static boolean check(int[] sel) {
        int sCount = 0;
        for (int i = 0; i < 7; i++) {
            int r = sel[i] / 5;
            int c = sel[i] % 5;
            if (map[r][c] == 'S') sCount++;
        }
        return sCount >= 4;
    }

    public static boolean bfs(int[] sel) {
        boolean[][] isSelected = new boolean[5][5];
        for (int i : sel) {
            isSelected[i / 5][i % 5] = true;
        }

        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new ArrayDeque<>();
        int start = sel[0];
        int sr = start / 5, sc = start % 5;
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                if (!visited[nr][nc] && isSelected[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    count++;
                }
            }
        }
        return count == 7;
    }
}
