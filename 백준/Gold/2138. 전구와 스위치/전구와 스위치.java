import java.util.*;

public class Main {
    static int N;
    static char[] original, target;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        original = sc.next().toCharArray();
        target = sc.next().toCharArray();

        int answer = sol();
        System.out.println(answer);
    }

    static int sol() {
        int res1 = solve(original.clone(), target, false);
        int res2 = solve(original.clone(), target, true);

        int answer = Math.min(res1, res2);
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }

    static int solve(char[] current, char[] target, boolean pressFirst) {
        int cnt = 0;

        if (pressFirst) {
            toggle(current, 0);
            cnt++;
        }

        for (int i = 1; i < N; i++) {
            if (current[i - 1] != target[i - 1]) {
                toggle(current, i);
                cnt++;
            }
        }

        return isSame(current, target) ? cnt : Integer.MAX_VALUE;
    }

    static void toggle(char[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i >= 0 && i < N) {
                arr[i] = arr[i] == '0' ? '1' : '0';
            }
        }
    }

    static boolean isSame(char[] a, char[] b) {
        for (int i = 0; i < N; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}
