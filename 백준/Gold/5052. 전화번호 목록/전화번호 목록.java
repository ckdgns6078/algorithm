import java.util.*;

class Trie {
    Map<Character, Trie> map;
    boolean lastNode;

    public Trie() {
        map = new HashMap<>();
        lastNode = false;
    }

    public void insert(String str) {
        Trie node = this;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            node.map.putIfAbsent(c, new Trie());
            node = node.map.get(c);
        }
        node.lastNode = true;
    }

    // 주어진 문자열이 다른 번호의 접두어인지 확인
    public boolean search(String str) {
        Trie node = this;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!node.map.containsKey(c)) {
                return false;
            }
            node = node.map.get(c);

            // 탐색 도중 다른 번호의 끝이 발견되면 접두어임
            if (node.lastNode && i != str.length() - 1) {
                return true;
            }
        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            Trie trie = new Trie();

            int n = sc.nextInt();
            String[] inputList = new String[n];

            for (int i = 0; i < n; i++) {
                inputList[i] = sc.next();
                trie.insert(inputList[i]);  // 모든 번호를 트라이에 삽입
            }

            boolean check = true;
            for (int i = 0; i < n; i++) {
                if (trie.search(inputList[i])) {
                    check = false;
                    break;
                }
            }

            if (!check) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
        sc.close();
    }
}