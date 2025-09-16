import java.util.*;

public class Main {

	static List<Integer>[] smallList;
	static List<Integer>[] bigList;

	static int smallCheck = 0;
	static int bigCheck = 0;
	static boolean[] smallVisited;
	static boolean[] bigVisited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		smallList = new ArrayList[N + 1];
		bigList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			smallList[i] = new ArrayList();
			bigList[i] = new ArrayList();
		}
		for (int i = 0; i < M; i++) {
			int small = sc.nextInt();
			int big = sc.nextInt();

			bigList[small].add(big);
			smallList[big].add(small);
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			smallCheck = 0;
			bigCheck = 0;

			smallVisited = new boolean[N + 1];
			bigVisited = new boolean[N + 1];

			smallDfs(i);
			bigDfs(i);

			if (smallCheck + bigCheck == N - 1) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	public static void smallDfs(int idx) {

		for (int i = 0; i < smallList[idx].size(); i++) {
			int nextIdx = smallList[idx].get(i);
			if (!smallVisited[nextIdx]) {
				smallDfs(nextIdx);
				smallCheck++;
				smallVisited[nextIdx] = true;
			}

		}
	}

	public static void bigDfs(int idx) {
		for (int i = 0; i < bigList[idx].size(); i++) {
			int nextIdx = bigList[idx].get(i);

			if (!bigVisited[nextIdx]) {
				bigDfs(nextIdx);
				bigCheck++;
				bigVisited[nextIdx] = true;
			}

		}
	}

}
