import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc =  0  ; tc < T ; tc++) {
			
			int n = sc.nextInt();	//동전의 갯수
			
			int[] arr =new int [n+1];
			for(int i = 1 ; i < n+1 ; i++) {
				arr[i] = sc.nextInt();
						
			}
			int money = sc.nextInt();
			
			int[][] dp = new int[n+1][money+1];
		
			for(int i = 0 ; i < n+1 ; i++) {
				dp[i][0] = 1;
			}
			
			for(int i = 1 ; i < n+1 ;i++) {
				for(int j = 1 ; j < money+1 ; j++) {
					if(j>=arr[i]) {
						dp[i][j]=dp[i-1][j]+dp[i][j-arr[i]];
					}else {
						dp[i][j]=dp[i-1][j];
					}
				}
				
			}
			
			System.out.println(dp[n][money]);
			
			
		}
		

	}

}
