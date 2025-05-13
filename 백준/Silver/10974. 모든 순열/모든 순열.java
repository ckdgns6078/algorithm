import java.util.*;


public class Main {

	static int n;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		visited = new boolean[n + 1];

		
		rec(new ArrayList());
		System.out.println(sb.toString());
	}
	
	public static void rec(List<Integer> list) {
		if(list.size()==n) {
			
			for( int i : list) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for(int i=1;i<=n;i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			list.add(i);
			rec(list);
			
			visited[i] =  false;
			list.remove(list.size()-1);
			
			
		}
		
		
	}
}
