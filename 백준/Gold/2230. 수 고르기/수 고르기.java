import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		long[] arr =new long[n];
		
		for(int i = 0 ; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int start=0;
		int end=0;
		long result = Long.MAX_VALUE;
		//시작값과 end 값중에서 뺸 값이 작을 경우 end를 증가시키고
		//시작값과 end
		
		if(n==1) {
			
		}
		
		while(end < n && start <= end) {
			long min = arr[end] - arr[start];
			if(min<m) {
				end++;
			}else {
				result = Math.min(result, min);
				start++;
			}
		}
		if(result == Long.MAX_VALUE) {
			System.out.println(arr[0]);
		}else {
			System.out.println(result);			
		}
		
		
	}
	 
}
