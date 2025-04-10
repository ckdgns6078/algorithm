import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N*N];
		
		for(int i=0;i<N*N;i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		int index = (N*N)-N;
		System.out.println(arr[index]);
		
	}
}
