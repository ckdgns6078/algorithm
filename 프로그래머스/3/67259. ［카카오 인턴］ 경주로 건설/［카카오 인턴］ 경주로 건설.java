import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int N = board.length;      // 행
        int M = board[0].length;   // 열
        
        // 방향: 0=상, 1=하, 2=좌, 3=우
        int[][][] visited = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        return bfs(N, M, board, visited);
    }
    
    public int bfs(int N, int M, int[][] board, int[][][] visited) {
        int answer = Integer.MAX_VALUE;
        int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dc = {0, 0, -1, 1};
        
        Queue<int[]> q = new ArrayDeque<>();
        for (int d = 1; d <= 3; d += 2) {
            visited[0][0][d] = 0;
            q.offer(new int[]{0, 0, 0, d});
        }
        
        while (!q.isEmpty()) {
            int[] p = q.poll();
            
            int r = p[0];
            int c = p[1];
            int pay = p[2];
            int dir = p[3];
            
            if (r == N - 1 && c == M - 1) {
                answer = Math.min(answer, pay);
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (board[nr][nc] == 1) continue;
                
                int newPay = pay + 100;
                if (dir != i) {
                    newPay += 500;
                }
                
                if (newPay < visited[nr][nc][i]) {
                    visited[nr][nc][i] = newPay;
                    q.offer(new int[]{nr, nc, newPay, i});
                }
            }
        }
        
        return answer;
    }
}
