import java.util.*;


public class Solution {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for( int tc = 1 ; tc <=T ; tc++) {
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			arr= new int[n+1];
			
			//초기화
			for( int i = 1 ; i < n+1 ; i++) {
				arr[i]=i;
			}
			
			
			
			for( int i = 0 ; i < m ; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				union(x,y);
			}
			
			
	           // 무리의 개수 계산
            int count = 0;
            for (int i = 1; i < n+1; i++) {
                if (arr[i] == i) {  // 루트 노드일 때
                    count++;
                }
            }
			
			
			System.out.println("#" + tc + " " + count);
			
			
		}

	}
	
	private static int find(int x) {
		if( arr[x] != x) {
			arr[x] = find(arr[x]);
		}
		return arr[x];
	}
	
	private static void union(int x , int y ) {
		int rx = find(x);
		int ry = find(y);
		
		if( rx != ry) {
			arr[ry] = rx;
		}
		
	}

}
