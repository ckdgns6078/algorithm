
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		combination(0 , 0 , new int[2]);
		System.out.println(answer);
	}
	
	
	
	public static void twoPoint(int[] sel) {
		int left = 0;
		int right = arr.length-1;
		int sum = arr[sel[0]] + arr[sel[1]];
	
		while(left < right) {
			
			if(sel[0]== left || sel[0]== right) {
				left++;
				continue;
			}
			if(sel[1]== left || sel[1] == right) {
				right--;
				continue;
			}
			
			int temp = arr[left] + arr[right];
			int abs = Math.abs(sum - temp);
			answer = Math.min(answer, abs);
			
			if(sum>temp) {
				left++;
			}else {
				right--;
			}
		}
	}
	
	
	
	public static void combination(int idx , int k , int[] sel) {
		if(sel.length== k) {
			twoPoint(sel);
			return;
		}
		
		if(idx == arr.length) {
			return;
		}
		
		sel[k] = idx;
		combination(idx+1 , k+1 , sel);
		combination(idx+1 , k , sel);
		
	}
	
	
	
	
}
