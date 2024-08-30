import java.util.*;

/**
 * 중복순열
 * 중복 O , 순서 O
 * 1,1 1,2 ,1,3 1,4 2,1 2,1 와 같이 출력
 */

public class Main {
	static int n,m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = i+1;
		}
		
		//       원본배열  생성배열      시작idx
		recursive(arr , new int[m] , 0);
		System.out.println(sb);
		
	}


	private static void recursive(int[] arr2, int[] sel, int k) {
		if( k == sel.length) {
			for (int i = 0; i < sel.length; i++){ 
				sb.append(sel[i]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for( int i = 0 ; i < arr2.length ; i++) {
			sel[k] = arr2[i];
			recursive(arr2 , sel , k+1);
		}
		
		
	}

}
