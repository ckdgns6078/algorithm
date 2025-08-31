import java.util.*;

public class Main {
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = sc.next().toCharArray();
        visited = new boolean[arr.length];
        
        solve(0, arr.length - 1);
    }

    static void solve(int l, int r) {
        if (l > r) return;

        int minIndex = l;
        for (int i = l; i <= r; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
    
        visited[minIndex] = true;
        print();

        solve(minIndex + 1, r); //오
        solve(l, minIndex - 1); //왼
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }
}