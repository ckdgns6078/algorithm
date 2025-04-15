import java.util.*;

public class Main {

	static int N;
	static int oneCnt = 0;
	static long answer =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		PriorityQueue<Integer> minusQueue = new PriorityQueue();
		PriorityQueue<Integer> plusQueue = new PriorityQueue(Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			int cnt = sc.nextInt();

			if (cnt == 1) {
				oneCnt++;
			} else if (cnt <= 0) {
				minusQueue.offer(cnt);
			} else {
				plusQueue.offer(cnt);
			}
		}

		int plusSize = plusQueue.size()/2;
		int minusSize = minusQueue.size()/2;
		
		for(int i=0;i<plusSize;i++) {
			answer += plusQueue.poll() * plusQueue.poll(); 
		}
		if(plusQueue.size()>0) {
			answer += plusQueue.poll();
		}
		
		
		for(int i=0;i<minusSize; i++) {
			answer += minusQueue.poll() * minusQueue.poll();
		}
		
		answer+= oneCnt;
		
		if(minusQueue.size()>0) {
			answer+= minusQueue.poll();
		}
		
		System.out.println(answer);
	}
}
