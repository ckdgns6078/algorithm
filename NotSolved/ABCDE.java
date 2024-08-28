package test;

import java.util.*;

public class ABCDE {

	static ArrayList<Integer>[] list;
	static boolean[] v;
	static boolean result = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		list = new ArrayList[n];
		v = new boolean[n];
		
		//list 초기화
		for( int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList();
		}
		
		for( int i = 0 ; i < m ; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
			list[b].add(a);
		}
		
		
		dfs(0 , 1);
		
		System.out.println(result ? 1 : 0);
	}
	private static void dfs(int idx, int cnt) {
		if( cnt ==5) {
			result= true;
			return;
		}
		
		for( int i=0 ; i < list[idx].size();i++) {
			int node = list[idx].get(i);
			if( !v[node]) {
				v[idx] = true;
				dfs(node , cnt+1);
				v[idx] = false;
			}
		}
	}

}
