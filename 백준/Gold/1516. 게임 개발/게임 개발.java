import java.util.*;

public class Main {

	static int N;
	static int[] times;
	static int[] indegree;
	static int[] dp;
	static List<Integer>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력부
		N = sc.nextInt();
		times = new int[N + 1];
		indegree = new int[N + 1];
		dp = new int[N + 1];
		list = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList();
		}

		for(int i=1;i<N+1;i++) {
			times[i] = sc.nextInt();
			
			while(true) {
				int cnt = sc.nextInt();
				if(cnt == -1) {
					break;
				}
				list[cnt].add(i);
				indegree[i]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque();
		
		for(int i=1;i<N+1;i++) {
			if(indegree[i]==0) {
				q.offer(i);
				dp[i]=times[i];
			}
		}
	
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(int next : list[p]) {
				dp[next] = Math.max(dp[next], dp[p] + times[next]);
				indegree[next]--;
				if(indegree[next]==0) {
					q.offer(next);
				}
				
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<N+1;i++) {
			sb.append(dp[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
