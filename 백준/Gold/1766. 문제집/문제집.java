import java.util.*;

public class Main {
	static int n; // 문제의 수
	static int m; // 먼저 푸는것이 좋은 문제
	static int[] table;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		table = new int[n + 1];
		list = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < m; i++) {
			int first = sc.nextInt();
			int end = sc.nextInt();
			list[first].add(end);
			table[end]++;
		}

		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue();
		for (int i = 1; i < n + 1; i++) {
			if (table[i] == 0) {
				pq.offer(i);
			}
		}

		while (!pq.isEmpty()) {
			int node = pq.poll();
			sb.append(node + " ");
			
			for (int i = 0; i < list[node].size(); i++) {
				int idx = list[node].get(i);
				table[idx]--;
				
				if(table[idx]==0) {
					pq.offer(idx);
				}
			}

		}
		System.out.println(sb);
	}

}
