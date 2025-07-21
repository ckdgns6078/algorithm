
import java.util.*;

public class Main {

	static int N;
	static int K;
	static int[] arr;
	static Set<List<Integer>> visited;
	static int answer = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		visited = new HashSet<>();
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		List<Integer> startKey = createKey(arr);
		visited.add(startKey);
		q.offer(new Node(arr.clone(), 0));

		while (!q.isEmpty()) {
			Node n = q.poll();

			if (check(n.arr)) {
				answer = n.cnt;
				return;
			}

			List<int[]> list = idxChange(n.arr);
			for (int[] temp : list) {
				List<Integer> key = createKey(temp);
				if (!visited.contains(key)) {
					q.offer(new Node(temp, n.cnt + 1));
					visited.add(key);
				}
			}
		}
	}

	public static List<int[]> idxChange(int[] arr) {
		List<int[]> list = new ArrayList<>();

		for (int i = 0; i <= N - K; i++) {
			int[] temp = arr.clone();
			reverse(temp, i, i + K - 1);
			list.add(temp);
		}

		return list;
	}

	public static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
	}

	public static boolean check(int[] temp) {
		for (int i = 0; i < temp.length - 1; i++) {
			if (temp[i] > temp[i + 1]) {
				return false;
			}
		}
		return true;
	}

	static class Node {
		int[] arr;
		int cnt;

		public Node(int[] arr, int cnt) {
			this.arr = arr;
			this.cnt = cnt;
		}
	}

	public static List<Integer> createKey(int[] temp) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			list.add(temp[i]);
		}
		return list;
	}
}