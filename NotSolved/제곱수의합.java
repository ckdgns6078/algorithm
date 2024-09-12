package test;

import java.util.*;

public class 제곱수의합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		

		int[] dp = new int[101010];
		
		dp[1]=1;
		dp[2]=2;
		dp[3]=3;
		dp[4]=1;
		dp[5]=2;
		dp[6]=3;
		dp[7]=4;
		dp[8]=1;
		dp[9]=1;
	
		
		for(int i = 10 ; i < n+1 ; i++) {
			
			int cnt = 3;
			int square = (int) Math.pow(cnt, 2);
			dp[i] = Integer.MAX_VALUE;
			while(square <= i) {
				if(square == i) {
					dp[i] = 1;
					break;
				}
				dp[i] = 1+Math.min(dp[i], dp[i-square]);
				cnt++;
				square = (int)Math.pow(cnt, 2);
				
			}
		}
		System.out.println(dp[n]);
	}

}
