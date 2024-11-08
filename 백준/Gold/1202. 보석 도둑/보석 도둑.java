import java.util.*;

public class Main {
	static int n;
	static int k;
	static int[] bags;
	static int[][] jewly;
	static long answer =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();
		bags = new int[k];
		jewly = new int[n][2];
		
		for(int i=0;i<n;i++) {
			int weight = sc.nextInt();
			int value = sc.nextInt();
			jewly[i][0] = weight;
			jewly[i][1] = value;
			
		}

		for(int i=0;i<k;i++) {
			bags[i] = sc.nextInt();
		}
		
		Arrays.sort(jewly ,(o1 , o2)->Integer.compare(o1[0] , o2[0]));
		Arrays.sort(bags);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int idx=0;
		
		for(int bag : bags) {
			while(idx < n && jewly[idx][0] <=bag) {
				pq.offer(jewly[idx][1]);
				idx++;
			}
			if(!pq.isEmpty()) {
				answer+= pq.poll();
			}
		}
		
		System.out.println(answer);
	}
}