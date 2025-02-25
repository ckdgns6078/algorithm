import java.util.*;

public class Main {
    static int N, M, K;
    static ArrayList<int[]>[] list;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList[N + 1];
        dp = new int[M + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int score = sc.nextInt();
            if (start < end) {
                list[start].add(new int[]{end, score});
            }
        }

        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], 0);
        }

        dp[1][1] = 0;
        int answer = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }

            for (int j = 1; j <= N; j++) {
                if (dp[i - 1][j] == 0 && j != 1) continue;

                for (int[] edge : list[j]) {
                    int end = edge[0], score = edge[1];

                    if (i < M) {
                        dp[i][end] = Math.max(dp[i][end], dp[i - 1][j] + score);
                    }
                }
            }
            answer = Math.max(answer, dp[i][N]);
        }


        System.out.println(answer);
    }
}
