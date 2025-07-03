import java.util.*;

public class Main {

	static List<Integer> list;
	static int N;
	static int[] arr;
	static Set<Integer> set;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = sc.nextInt();

		}

		list = new ArrayList();

		for (int i = 1; i < N + 1; i++) {
			set = new HashSet();
			dfs(i, i);
		}

		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void dfs(int start, int now) {
		if (set.contains(now)) {

			if (now == start) {
				for(int i : set) {
					if(!list.contains(i)) {
						list.add(i);
					}
				}
			}
			return;
		}

		set.add(now);
		dfs(start, arr[now]);

	}
}
