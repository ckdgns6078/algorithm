import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] arr = new int[n+1];
		int cnt =0;


		for(int i=2;i<=n;i++) {
			
			for(int j=i;j<=n ; j+=i) {
				if(arr[j]==0) {
					arr[j]=1;
					cnt++;
				}
				
				
				if(cnt == k) {
					System.out.println(j);
					System.exit(0);
				}
			}
			
		}
		
		
	}

}
