import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[] parent;
	static int[] travel;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N= sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N+1][N+1];
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parent[i] = i;
		}
		
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				int input = sc.nextInt();
				if(input == 1) {
					union(i , j);
				}
			}
		}
		
		travel = new int[M];
		for(int i=0;i<M;i++) {
			travel[i] = sc.nextInt();
			
		}
	
		boolean temp = true;
		for(int i=0;i<M-1;i++) {
			
			if(!isSame(travel[i] , travel[i+1])) {
				temp = false;
				break;
			}
		}
		System.out.println(temp == true ? "YES" : "NO");
	}
	
	public static int find(int x) {
		if(parent[x]==x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX != rootY) {
			parent[rootY] = rootX;
		}
	}
	
	public static boolean isSame(int x , int y) {
		return find(x) == find(y);
	}
	
	
}
