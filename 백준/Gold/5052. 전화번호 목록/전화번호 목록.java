import java.util.*;

class Trie {
    Map<Character, Trie> map;
    boolean lastNode;

    public Trie() {
        map = new HashMap<>();
        lastNode = false;
    }

    public boolean insert(String str) {
        Trie node = this;
        boolean isNew = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!node.map.containsKey(c)) {
                isNew = true;
                node.map.put(c, new Trie());
            }
            node = node.map.get(c);

            // 다른 번호가 현재 번호의 접두어인 경우
            if (node.lastNode) {
                return false;
            }
        }

        // 이미 해당 문자열이 접두어인 경우
        if (!isNew) {
            return false;
        }

        node.lastNode = true;
        return true;
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
            boolean consistent = true;

            for (int i = 0; i < n; i++) {
                inputList[i] = sc.next();
            }

            for (int i = 0; i < n; i++) {
                if (!trie.insert(inputList[i])) {
                    consistent = false;
                    break;
                }
            }

            if (consistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}