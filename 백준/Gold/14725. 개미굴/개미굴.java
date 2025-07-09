import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Trie trie = new Trie();

		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			List<String> list = new ArrayList<>();
			for (int j = 0; j < temp; j++) {
				list.add(sc.next());
			}
			trie.insert(list);
		}

		trie.search();
	}

	static class Trie {
		Node root;

		Trie() {
			this.root = new Node();
		}

		public void insert(List<String> list) {
			Node node = this.root;

			for (String str : list) {
				if (!node.child.containsKey(str)) {
					node.child.put(str, new Node());
				}
				node = node.child.get(str);
			}
		}

		public void search() {
			dfs(this.root, 0);
		}

		private void dfs(Node node, int depth) {
			for (String key : node.child.keySet()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < depth; i++) {
					sb.append("--");
				}
				sb.append(key);
				System.out.println(sb.toString());

				dfs(node.child.get(key), depth + 1);
			}
		}
	}

	static class Node {
		Map<String, Node> child;

		Node() {
			this.child = new TreeMap<>();
		}
	}
}
