import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			int n = sc.nextInt(); // 팀의 갯수
			int k = sc.nextInt(); // 문제의 갯수
			int t = sc.nextInt(); // 나의 팀
			int m = sc.nextInt(); // 엔트리의 숫자

			Team[] teams = new Team[n + 1];
			for (int i = 1; i < n + 1; i++) {
				teams[i] = new Team(i);
			}

			for (int i = 0; i < m; i++) {
				int tt = sc.nextInt();
				int problem = sc.nextInt();
				int score = sc.nextInt();

				/**
				 * 1. map에 데이터가 있는지 검사를 한다. 2. cnt를 증가시킨다 submit을 증가시킨다.
				 */

				if (teams[tt].map.containsKey(problem)) {
					// 있을경우 더 큰값으로 변경하고 score를 감소시킨다.
					if (score > teams[tt].map.get(problem)) {
						teams[tt].totalScore -= teams[tt].map.get(problem);
						teams[tt].map.put(problem, score);
						teams[tt].totalScore += score;
					}
				} else {
					teams[tt].map.put(problem, score);
					teams[tt].totalScore += score;
				}
				teams[tt].cnt++;
				teams[tt].submit = i;
			}

			PriorityQueue<Team> pq = new PriorityQueue();
			for (int i = 1; i < n + 1; i++) {
				pq.offer(teams[i]);
			}

			int rank = 1;
			while (!pq.isEmpty()) {
				Team result = pq.poll();
//				System.out.println("등수 : " + rank + " , 팀 : " + result.index);
				if (result.index == t) {
					sb.append(rank).append("\n");
					break;
				}
				rank++;
			}
		}
		System.out.println(sb.toString());

	}

	static class Team implements Comparable<Team> {
		int index;
		Map<Integer, Integer> map;
		int totalScore;
		int cnt;
		int submit;

		Team(int index) {
			this.index = index;
			this.map = new HashMap();
			this.totalScore = 0;
			this.cnt = 0;
			this.submit = 0;
		}

		@Override
		public int compareTo(Team o) {
			if (this.totalScore != o.totalScore) {
				return Integer.compare(o.totalScore, this.totalScore);
			} else if (this.cnt != o.cnt) {
				return Integer.compare(this.cnt, o.cnt);
			}
			return Integer.compare(this.submit, o.submit);
		}

	}
}
