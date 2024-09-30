import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int tc =1 ; tc<=T ; tc++) {
			
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[] volume = new int[N+1];	//부피
			int[] value = new int[N+1];		//가치
			
			int[][] dp = new int[110][1100];
			
			for(int i = 1 ; i < N+1 ; i++) {
				volume[i] = sc.nextInt();
				value[i] = sc.nextInt(); 
			}
			
			
			// j = 부피가 된다. 부피보다 작으면 가능하도록 설정
			
			//volume 이 작을때만 더해야된다.
			for(int i = 1 ; i <N+1 ; i++) {
				for(int j = 0 ; j < K+1 ; j++) {
					//무게를 초과하지 않도록
					if(j>=volume[i]) {
						dp[i][j] = Math.max(value[i]+dp[i-1][j-volume[i]], dp[i-1][j]);
					}else {
						dp[i][j]= dp[i-1][j];
					}
				}
			}
			
			System.out.println("#" + tc +" " + dp[N][K]);
		}
	}

}
