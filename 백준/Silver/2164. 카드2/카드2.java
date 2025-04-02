import java.util.*;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Queue<Integer> q = new ArrayDeque();
	
		N = sc.nextInt();
		
		for(int i=1;i<=N;i++) {
			q.offer(i);
		}
		
		
		int size = q.size();
		while(size!=1) {
			
			//바닥에 버린다
			q.poll();
			
			if(q.size()==1) {
				break;
			}
			int temp = q.poll();
			q.offer(temp);
			
		}
		
		System.out.println(q.poll());
	}
}
