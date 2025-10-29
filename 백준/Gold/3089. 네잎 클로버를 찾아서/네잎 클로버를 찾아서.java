import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Map<Integer, TreeSet<Integer>> vertical = new HashMap<>();
        Map<Integer, TreeSet<Integer>> horizontal = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            vertical.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
            horizontal.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
        }

        String commands = sc.next();
        int x = 0, y = 0;

        for (char cmd : commands.toCharArray()) {
            if (cmd == 'U') {
                y = vertical.get(x).higher(y);
            } else if (cmd == 'D') {
                y = vertical.get(x).lower(y);
            } else if (cmd == 'R') {
                x = horizontal.get(y).higher(x);
            } else if (cmd == 'L') {
                x = horizontal.get(y).lower(x);
            }
        }

        System.out.println(x + " " + y);
    }
}
