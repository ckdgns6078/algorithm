import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine(); // consume the remaining newline
        
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }
        
        if (N == 1) {
            // 초기 상태 출력
            printGrid(grid);
        } else if (N % 2 == 0) {
            // 모든 칸에 폭탄 설치된 상태 출력
            printFullBombs(R, C);
        } else {
            // 폭탄이 폭발한 상태 계산
            char[][] state3 = simulateExplosion(grid, R, C);
            if (N % 4 == 3) {
                printGrid(state3);
            } else {
                // N % 4 == 1인 경우
                char[][] state5 = simulateExplosion(state3, R, C);
                printGrid(state5);
            }
        }
        sc.close();
    }
    
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }
    
    private static void printFullBombs(int R, int C) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print('O');
            }
            System.out.println();
        }
    }
    
    private static char[][] simulateExplosion(char[][] grid, int R, int C) {
        char[][] result = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result[i][j] = 'O';
            }
        }
        
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'O') {
                    result[i][j] = '.';
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni >= 0 && ni < R && nj >= 0 && nj < C) {
                            result[ni][nj] = '.';
                        }
                    }
                }
            }
        }
        
        return result;
    }
}

// 2 : 터진값

// 3 : 한번 더 터진값

// 모두 O인 값
