import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		int[] result =new int[2];
		
		for(int i=0; i<n ;i++) {
			arr[i] = sc.nextInt();
		}
		
		
		int start =0;
		int end = n-1;
		long min = Long.MAX_VALUE;
		
		while(start < end) {
			long sum = arr[start] + arr[end];
			
			long abs = Math.abs(sum);
			if(abs < min) {
				result[0] = start;
				result[1] = end;
				min = abs;
			}
			
			if(sum ==0) {
				break;
			}else if(sum < 0) {
				start++;
			}else {
				end--;
			}
		}
		
		System.out.println(arr[result[0]] + " " + arr[result[1]]);
		
		
	}

}
