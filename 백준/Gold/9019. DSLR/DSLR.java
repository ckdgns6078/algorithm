import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            int input = sc.nextInt();
            int answer = sc.nextInt();

            boolean[] visited = new boolean[10000];
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(input, ""));
            visited[input] = true;

            while (!q.isEmpty()) {
                Point p = q.poll();
                int number = p.temp;
                String value = p.value;

                if (number == answer) {
                    sb.append(value).append("\n");
                    break;
                }

                // 명령어 D
                int next = D(number);
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Point(next, value + "D"));
                }

                // 명령어 S
                next = S(number);
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Point(next, value + "S"));
                }

                // 명령어 L
                next = L(number);
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Point(next, value + "L"));
                }

                // 명령어 R
                next = R(number);
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Point(next, value + "R"));
                }
            }
        }

        System.out.print(sb);
    }

    public static int D(int temp) {
        return (temp * 2) % 10000;
    }

    public static int S(int temp) {
        return temp == 0 ? 9999 : temp - 1;
    }

    public static int L(int temp) {
        return (temp % 1000) * 10 + temp / 1000;
    }

    public static int R(int temp) {
        return (temp % 10) * 1000 + (temp / 10);
    }

    static class Point {
        int temp;
        String value;

        Point(int temp, String value) {
            this.temp = temp;
            this.value = value;
        }
    }
}
