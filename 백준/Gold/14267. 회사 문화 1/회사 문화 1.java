import java.util.*;

public class Main {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int orner = sc.nextInt();
            if (orner == -1) continue;

            if (!map.containsKey(orner)) {
                map.put(orner, new ArrayList<>());
            }
            map.get(orner).add(i);
        }

        for (int i = 0; i < m; i++) {
            int emp = sc.nextInt();
            int score = sc.nextInt();
            arr[emp] += score;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void dfs(int current) {
        if (!map.containsKey(current)) return;
        for (int child : map.get(current)) {
            arr[child] += arr[current];
            dfs(child);
        }
    }
}
