
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static long answer =Long.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		for(int i=0;i<N;i++) {
			long value = (N-i) * arr[i];
			answer = Math.max(value, answer);
		}
		System.out.println(answer);
	}
}
