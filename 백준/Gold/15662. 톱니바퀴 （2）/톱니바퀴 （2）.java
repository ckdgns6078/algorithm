
import java.util.*;

public class Main {
    static int[][] gears;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        gears = new int[n + 1][8];
        
        for (int i = 1; i <= n; i++) {
            String input = sc.next();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = input.charAt(j) - '0';
            }
        }
        
        int k = sc.nextInt();
        
        for (int i = 0; i < k; i++) {
            int gearNumber = sc.nextInt();
            int direction = sc.nextInt();
            
            visited = new boolean[n + 1];
            checkRotation(gearNumber, direction);
        }
        
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (gears[i][0] == 1) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static void checkRotation(int idx, int dir) {
        visited[idx] = true;
        
        // 좌측 톱니바퀴 회전 여부 확인
        if (idx > 1 && !visited[idx - 1] && gears[idx][6] != gears[idx - 1][2]) {
            checkRotation(idx - 1, -dir);
        }
        
        // 우측 톱니바퀴 회전 여부 확인
        if (idx < gears.length - 1 && !visited[idx + 1] && gears[idx][2] != gears[idx + 1][6]) {
            checkRotation(idx + 1, -dir);
        }
        
        rotate(idx, dir);
    }

    private static void rotate(int idx, int dir) {
        if (dir == 1) { // 시계 방향 회전
            int last = gears[idx][7];
            for (int i = 7; i > 0; i--) {
                gears[idx][i] = gears[idx][i - 1];
            }
            gears[idx][0] = last;
        } else { // 반시계 방향 회전
            int first = gears[idx][0];
            for (int i = 0; i < 7; i++) {
                gears[idx][i] = gears[idx][i + 1];
            }
            gears[idx][7] = first;
        }
    }
}