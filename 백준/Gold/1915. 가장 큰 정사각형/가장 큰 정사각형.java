import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] dp;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        max = 0;
        boolean check = false;

        // 배열 입력
        for (int i = 1; i <= n; i++) {
            String input = sc.next();
            for (int j = 1; j <= m; j++) {  // j+1로 수정하여 1-based index 적용
                arr[i][j] = input.charAt(j - 1) - '0';  // 1-based index를 위해 j-1 사용
                if (arr[i][j] == 1) {
                    check = true;  // 1이 하나라도 있으면 true
                }
            }
        }

        // DP 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 1) {  // arr[i][j]가 1일 때만 계산
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);  // 최대 변의 길이 갱신
                }
            }
        }

        // 출력
        if (!check) {
            System.out.println(0);
        } else {
            System.out.println(max * max);  // 넓이를 출력해야 하므로 변의 길이의 제곱을 출력
        }
    }
}