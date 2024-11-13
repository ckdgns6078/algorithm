import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int m;
    static int[][] order;
    static int[][] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        m = sc.nextInt();
        order = new int[m + 1][2];
        for (int i = 1; i <= m; i++) {
            order[i][0] = sc.nextInt();
            order[i][1] = sc.nextInt();
        }

        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        for (int length = 3; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            int S = order[i][0];
            int E = order[i][1];
            sb.append(dp[S][E]).append("\n");
        }

        System.out.print(sb);
    }
}
