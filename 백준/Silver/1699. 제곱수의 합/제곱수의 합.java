import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		

		int[] dp = new int[101010];
		
		dp[1]=1;
		dp[2]=2;
		dp[3]=3;

		
		for(int i = 4 ; i < n+1 ; i++) {
			 
			int num = 2;
			int sum = (int)Math.pow(num, 2);
			dp[i] = Integer.MAX_VALUE;
			while(sum<=i) {
				dp[i] = Math.min(dp[i],1+dp[i-sum]);
				num++;
				sum = (int)Math.pow(num, 2);
			}
		}
		
		
		System.out.println(dp[n]);
	}

}
