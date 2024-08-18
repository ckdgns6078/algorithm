
import java.util.*;

public class Main {
	static int n , m;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		n= sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = i+1;
		}
		
		recursive(0 , 0 , new int[m]);
		
		
		
	}

	private static void recursive(int idx, int k, int[] sel) {
		if( k == sel.length) {
			StringBuffer sb = new StringBuffer();
			for( int i = 0 ; i < sel.length ; i++) {
				sb.append(sel[i]);
				sb.append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		if( idx == n) {
			return;
		}
		
		sel[k] = arr[idx];
		recursive(idx+1 , k+1 , sel);
		recursive(idx+1 , k , sel);
		
		
	}

}
