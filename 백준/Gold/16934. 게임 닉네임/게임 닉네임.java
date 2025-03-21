import java.util.*;

class Node {
	Map<Character, Node> child;
	boolean end;

	public Node() {
		this.child = new HashMap<>();
		this.end = false;
	}
}

class Trie {
	Node root;

	public Trie() {
		this.root = new Node();
	}

	public String insert(String str) {
		String nickName = "";
		boolean check = false;
		Node node = this.root;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (!check) {
				nickName += c;
			}

			if (!node.child.containsKey(c)) {
				check = true;
			}

			node.child.putIfAbsent(c, new Node());
			node = node.child.get(c);
		}
		node.end = true;
		return nickName;
	}

	public void search() {

	}
}

public class Main {
	static int N;
	static Map<String, Integer> map;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Trie trie = new Trie();
		map = new HashMap<>();
		sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			String nickName = trie.insert(str);
			boolean check = false;

			if (!map.containsKey(str)) {
				map.put(str, 2);
				check = true;
			}

			sb.append(nickName);
			if (str.equals(nickName) && !check) {
				sb.append(map.get(nickName));
				map.put(nickName, map.get(nickName) + 1);
			}

			sb.append("\n");
		}
		System.out.println(sb);
	}
}
