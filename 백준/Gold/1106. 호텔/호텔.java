import java.util.*;

public class Main {

	
	public static void main(String[] args) {
		Scanner sc=  new Scanner(System.in);
		
		int C = sc.nextInt();
		int N = sc.nextInt();
		
		int[] dp = new int[C + 101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0]= 0;
		
		for(int i=0;i<N;i++) {
			int cost = sc.nextInt();
			int guest = sc.nextInt();
			
			for(int j = guest ; j < dp.length;j++) {
				if(dp[j-guest]!=Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j-guest]+cost);
				}
			}
		}
		
		int answer =Integer.MAX_VALUE;
		for(int i=C;i<dp.length;i++) {
			answer = Math.min(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
