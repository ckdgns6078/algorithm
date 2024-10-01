import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int s = sc.nextInt();
		
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		
		int start = 0;
		int end = 0;
		int answer = Integer.MAX_VALUE;
		int prefixSum =arr[start];
		while(end < n && start <= end) {
			
			if(prefixSum >= s) {
				answer = Math.min(answer, end-start+1);
			}
			
			if(prefixSum < s) {
				end++;
				if(end < n) {
					prefixSum += arr[end];					
				}
			}else {
				prefixSum-= arr[start];
				start++;
			}
		}
		
		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);

	}

}
