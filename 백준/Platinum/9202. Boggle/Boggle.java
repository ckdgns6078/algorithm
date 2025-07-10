import java.util.*;

public class Main {

    static Set<String> resultSet;
    static String[][] map;
    static Trie trie;
    static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        trie = new Trie();
        for (int i = 0; i < N; i++) {
            String input = sc.next();
            trie.insert(input);
        }

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            map = new String[4][4];
            for (int j = 0; j < 4; j++) {
                String input = sc.next();
                map[j] = input.split("");
            }

            resultSet = new HashSet<>();

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    boolean[][] visited = new boolean[4][4];
                    visited[r][c] = true;
                    find(r, c, map[r][c], visited);
                }
            }

            int biggestScore = 0;
            String longestWord = "";
            int findSum = resultSet.size();

            for (String str : resultSet) {
                biggestScore += score(str);

                if (str.length() > longestWord.length() || 
                    (str.length() == longestWord.length() && str.compareTo(longestWord) < 0)) {
                    longestWord = str;
                }
            }

            System.out.println(biggestScore + " " + longestWord + " " + findSum);
        }
    }

    public static int score(String str) {
        int len = str.length();
        if (len >= 8) return 11;
        if (len == 7) return 5;
        if (len == 6) return 3;
        if (len == 5) return 2;
        if (len == 4 || len == 3) return 1;
        return 0;
    }

    public static void find(int r, int c, String current, boolean[][] visited) {
        if (!trie.startsWith(current)) return;

        if (trie.contains(current)) {
            resultSet.add(current);
        }

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                find(nr, nc, current + map[nr][nc], visited);
                visited[nr][nc] = false;
            }
        }
    }

    static class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                node = node.child.computeIfAbsent(c, k -> new Node());
            }
            node.end = true;
        }

        public boolean contains(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                node = node.child.get(c);
                if (node == null) return false;
            }
            return node.end;
        }

        public boolean startsWith(String prefix) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                node = node.child.get(c);
                if (node == null) return false;
            }
            return true;
        }
    }

    static class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean end = false;
    }
}
