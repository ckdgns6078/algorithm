
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        run(0, 0, N);
        System.out.println(sb.toString());
    }

    public static void run(int r, int c, int size) {
        if (check(r, c, size)) {
            sb.append(map[r][c]);
            return;
        }

        int halfSize = size / 2;
        sb.append("(");
        run(r, c, halfSize);                        // 왼쪽 위
        run(r, c + halfSize, halfSize);             // 오른쪽 위
        run(r + halfSize, c, halfSize);             // 왼쪽 아래
        run(r + halfSize, c + halfSize, halfSize);  // 오른쪽 아래
        sb.append(")");
    }

    public static boolean check(int r, int c, int size) {
        int temp = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != temp) {
                    return false;
                }
            }
        }
        return true;
    }
}
