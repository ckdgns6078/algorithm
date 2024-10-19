import java.util.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T ; tc++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			int[] table = new int[n+1];
			int[] pay = new int[n+1];
			int[] dp = new int[n+1];
			//비용 넣기
			for(int i=1 ; i < n+1 ; i++) {
				pay[i] = sc.nextInt();
				dp[i] = pay[i];
			}
			
			ArrayList<Integer>[] list = new ArrayList[n+1];
			for(int i=0;i<n+1 ; i++) {
				list[i] = new ArrayList();
			}
			
			for(int i=0;i<k;i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				table[end]++;
				list[start].add(end);
			}
			int point = sc.nextInt();
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int i=1 ; i<n+1 ; i++) {
				if(table[i]==0) {
					q.offer(i);
				}
			}
			
			if(table[point]==0) {
				sb.append(pay[point]);
				sb.append("\n");
				continue;
			}
			
			int result =0;
			L :while(!q.isEmpty()) {
				
				int size = q.size();

				for(int i=0; i< size ;i++) {
					int p= q.poll();
					if(p==point) {
						result+= pay[point];
						break L;
					}
					
					for(int j=0; j < list[p].size() ; j++) {
						int node = list[p].get(j);
						table[node]--;
						 dp[node] = Math.max(dp[node], dp[p] + pay[node]);
						if(table[node]==0) {
							q.offer(node);
						}
					}
				}
			}
			sb.append(dp[point]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	

}
