import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = sc.nextInt();

		for (int tc = 0; tc < testCase; tc++) {
			Map<Integer, Integer> node = new HashMap();
			int n = sc.nextInt();
			for (int i = 0; i < n-1; i++) {
				int a = sc.nextInt(); // 부모
				int b = sc.nextInt(); // 자식
				node.put(b, a);
			}

			int find1 = sc.nextInt();
			int find2 = sc.nextInt();
			List<Integer> find1List = dfs(find1, node);
			List<Integer> find2List = dfs(find2, node);
			
			for(int idx : find1List) {
				if(find2List.contains(idx)) {
					sb.append(idx).append("\n");
					break;
				}
			}
		}
		System.out.println(sb.toString());
	}

	public static List<Integer> dfs(int start, Map<Integer, Integer> map) {
		List<Integer> resultList = new ArrayList();
		Queue<Integer> q = new ArrayDeque();
		q.offer(start);
		resultList.add(start);
		while (!q.isEmpty()) {
			int node = q.poll();

			if (!map.containsKey(node)) {
				resultList.add(node);
				return resultList;
			}
			if(node != start) {
				resultList.add(node);
			}
			q.offer(map.get(node));
		}
		return resultList;
	}
}
