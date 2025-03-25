import java.util.*;

public class Main {

	static int N;
	static ArrayList<String[]> inputList;
	static ArrayList<Trie> trieList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		inputList = new ArrayList();
		trieList = new ArrayList();

		// 초기값 입력 받기
		while (sc.hasNext()) {
			int input = sc.nextInt();
			String[] arr = new String[input];
			Trie trie = new Trie();

			for (int i = 0; i < input; i++) {
				arr[i] = sc.next();
				trie.insert(arr[i]);
			}
			inputList.add(arr);
			trieList.add(trie);
		}

		int size = trieList.size();

		for (int i = 0; i < size; i++) {
			double answer = 0;
			Trie trie = trieList.get(i);
			int temp = inputList.get(i).length;
			for (int j = 0; j < inputList.get(i).length; j++) {
				String input = inputList.get(i)[j];
				answer += trie.module(input);
			}
			answer /= temp;
			System.out.printf("%.2f\n", answer);
		}

	}

	static class Node {
		Map<Character, Node> child;
		boolean end;

		public Node() {
			this.child = new HashMap();
			this.end = false;
		}
	}

	static class Trie {
		private Node root;

		public Trie() {
			this.root = new Node();
		}

		public void insert(String str) {
			Node current = root;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (!current.child.containsKey(c)) {
					current.child.put(c, new Node());
				}
				current = current.child.get(c);
			}
			current.end = true;
		}

		public int module(String str) {
			int cnt = 1;
			Node current = this.root;
			current = current.child.get(str.charAt(0));

			for (int i = 1; i < str.length(); i++) {
				char c = str.charAt(i);

				if (current.child.size() > 1 || current.end) {
					cnt++;
				}

				current = current.child.get(c);
			}
			return cnt;
		}

	}
}
