import java.util.Scanner;

public class Main {
    static int n, s, count = 0;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        dfs(0, 0);

        // 공집합 제거
        if (s == 0) count--;

        System.out.println(count);
    }

    public static void dfs(int idx, int sum) {
        if (idx == n) {
            if (sum == s) count++;
            return;
        }

        // 현재 원소 포함
        dfs(idx + 1, sum + arr[idx]);

        // 현재 원소 미포함
        dfs(idx + 1, sum);
    }
}
