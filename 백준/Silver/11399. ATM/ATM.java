import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int answer =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int sum =0;
		for(int i=0;i<N;i++) {
			answer += arr[i] + sum;
			sum += arr[i];
		}
		
		System.out.println(answer);
	}
}
