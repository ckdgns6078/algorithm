import java.util.*;

public class Main {

	static int n;
	static int k;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		int sum = 0;
		
		n = sc.nextInt();
		k = sc.nextInt();
		arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=0;i<k;i++) {
			sum+=arr[i];
		}
		int result = sum;
		
		for(int i=1;i<=n-k;i++) {
			sum = sum - arr[i-1] + arr[i+k-1];
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}

}
