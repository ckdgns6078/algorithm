import java.util.*;


public class Main {

	static int n;
	static int m;
	
	static int[] parent;
	static int[] sum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		parent = new int[n+1];
		
		for(int i=0;i<=n;i++) {
			parent[i] = i;
		}
		
		StringBuilder sb=  new StringBuilder();
		
		for(int i=0;i<m;i++) {
			int t = sc.nextInt();
			int a= sc.nextInt();
			int b= sc.nextInt();
			
			
			if(t ==0) {
				union(a,b);
				
			}else {
				if(find(a) ==find(b)) {
					sb.append("YES");
				}else {
					sb.append("NO");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		
		
		
		

	}
	
	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX != rootY) {
			parent[rootY] = rootX;
		}
	}
	
	public static int find(int x) {
		if(parent[x] !=x) {
			return find(parent[x]);
		}
		return parent[x];
	}
	
	

}
