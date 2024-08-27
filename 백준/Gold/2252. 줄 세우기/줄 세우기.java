
import java.util.*;

public class Main {
	static int n, m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		StringBuffer sb = new StringBuffer();

		// 1부터 시작
		ArrayList<Integer>[] list = new ArrayList[n + 1];
		int[] inDegree = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < m; i++) {
			int cnt1 = sc.nextInt();
			int cnt2 = sc.nextInt();
			list[cnt1].add(cnt2);
			inDegree[cnt2]++;
		}

		Queue<Integer> q = new ArrayDeque<Integer>();

		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		List<Integer> resultList = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			int p = q.poll();
			resultList.add(p);
			sb.append(p + " ");
			for (int i = 0; i < list[p].size(); i++) {
				int temp = list[p].get(i);

				inDegree[temp]--;
				if (inDegree[temp] == 0) {
					q.add(temp);
				}
			}

		}


		System.out.println(sb);
	}

}
