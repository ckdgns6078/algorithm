import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static boolean[] visited;
	static boolean[] team;
	static int answer;

//	static ArrayList<Integer> list = new ArrayList();
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			n = sc.nextInt();
			arr = new int[n + 1];
			visited = new boolean[n + 1];
			team = new boolean[n + 1];
			answer = n;

			for (int i = 1; i < n + 1; i++) {
				arr[i] = sc.nextInt();
				if (i == arr[i]) {
					team[i] = true;
					visited[i] = true;
					answer--;
				}
			}

			for (int i = 1; i < n + 1; i++) {
				if (!team[i]) {
					findTeam(i);

				}
			}

			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void findTeam(int idx) {
		if (visited[idx])
			return;

		ArrayList<Integer> list = new ArrayList();
		int startCnt = idx;

		while (!visited[startCnt]) {
			visited[startCnt] = true;
			list.add(startCnt);
			startCnt = arr[startCnt];
		}

		if (list.contains(startCnt)) {
			int startIdx = list.indexOf(startCnt);
			int cnt = 0;
			for (int i = startIdx; i < list.size(); i++) {
				team[list.get(i)] = true;
				cnt++;
			}
			answer -= cnt;
		}

	}

}
