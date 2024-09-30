import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] size = new int[N+1];
		int[] value = new int[N+1];
		
		long[][] dp = new long[110][110000];
		
		for(int i = 1 ; i < N+1 ; i++) {
			size[i] = sc.nextInt();
			value[i] = sc.nextInt();
		}
		
		for(int i = 1 ; i < N+1 ; i++) {
			for(int j = 0 ; j <K+1 ; j++) {
				if(j>=size[i]) {
					dp[i][j] = Math.max(value[i]+dp[i-1][j-size[i]], dp[i-1][j]);
				}else {
					dp[i][j]= dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
	}

}
