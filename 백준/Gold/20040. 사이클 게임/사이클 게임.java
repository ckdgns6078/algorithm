import java.util.*;


public class Main {
	static int[] parent;
	static int N , M;
	
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		parent = new int[N];
		for(int i=0;i<N;i++) {
			parent[i] = i;
		}
		
		
		int answer =0;
		
		for(int i=0;i<M;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if(isSame(x,y)) {
				answer = i+1;
				break;
			}
			union(x,y);
			
		}
		
		
		System.out.println(answer);
		
		
	}

	public static int find(int x) {
		if(x == parent[x]) {
			return x;
		}else {
			return parent[x] = find(parent[x]);
		}
	}
	
	public static void union(int x , int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX != rootY) {
			parent[rootY] = rootX;
		}
	}
	
	
	public static boolean isSame(int x,  int y) {
		if(find(x) == find(y)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
