import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();  // 동전의 종류 수
        int k = sc.nextInt();  // 목표 금액
        
        int[] arr = new int[n + 1];  // 동전 배열
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // dp 배열 초기화: Integer.MAX_VALUE로 초기화하여 동전 교환 불가능한 경우를 나타냄
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;  // 0원을 만들기 위한 동전 개수는 0
        
        // 동전마다 dp 배열을 갱신
        for (int i = 1; i <= n; i++) {
            for (int j = arr[i]; j <= k; j++) {
                if (dp[j - arr[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
                }
            }
        }
        
        // 결과 출력
        if (dp[k] == Integer.MAX_VALUE) {
            System.out.println(-1);  // 목표 금액을 만들 수 없을 때
        } else {
            System.out.println(dp[k]);  // 최소 동전 개수 출력
        }
    }
}