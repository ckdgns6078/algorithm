
import java.util.*;

public class Main {
	static int n;
	static long[] arr;
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr= new long[n];
		for(int i=0; i<n;i++) {
			arr[i] = sc.nextLong();
		}
		
		Arrays.sort(arr);
		int[] result = new int[3];
		Arrays.fill(result, Integer.MAX_VALUE);
		long min = Long.MAX_VALUE;
		
		
		for(int i=0; i < n-2 ; i++) {
			int start = i+1;
			int end = n-1;
			
			while(start<end) {
				long sum = arr[i] + arr[start]+arr[end];
				
				long abs = Math.abs(sum);
				if(abs < min) {
					min = abs;
					result[0]=i;
					result[1] = start;
					result[2] = end;
				}
				if(sum==0) {
					break;
				} else if( sum>0) {
					end--;
				}else {
					start++;
				}
			}
	}
		Arrays.sort(result);
		System.out.println(arr[result[0]] + " " + arr[result[1]] + " " + arr[result[2]]);
	}
}
