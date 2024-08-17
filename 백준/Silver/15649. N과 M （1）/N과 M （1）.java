import java.util.*;


public class Main {
	static int[] map;
	static int[] sel;
	static int n , m;
	static boolean[] v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n];
		v = new boolean[n];
		
		for( int i = 0 ; i < n ; i++) {
			map[i] = i+1;
		}
		
		recursive(new int[m] , 0);
		
		
	}
	private static void recursive(int[] sel, int k) {
		
		if( k == sel.length) {
			StringBuilder sb = new StringBuilder();
			for( int i = 0 ; i < sel.length ; i++) {
				sb.append(sel[i]);
				sb.append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		for( int i = 0 ; i < map.length;i++) {
			if(v[i] == false) {
				v[i] = true;
				sel[k] = map[i];
				recursive(sel , k+1);
				v[i] = false;
			}
		}
		
	}



	
	
	
}
