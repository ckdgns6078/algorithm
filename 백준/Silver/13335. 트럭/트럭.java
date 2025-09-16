import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int w = sc.nextInt();
		int L = sc.nextInt();

		Queue<Integer> q = new ArrayDeque();
		for (int i = 0; i < n; i++) {
			int weight = sc.nextInt();
			q.offer(weight);
		}

		int time = 0;
		int nowWeight = 0;
		int endCount = 0;
		Queue<int[]> crossQueue = new ArrayDeque();

		while (endCount < n) {

			if (!crossQueue.isEmpty()) {
				int[] crossTruck = crossQueue.peek();
				if (crossTruck[0] == time) {
					crossQueue.poll();
					nowWeight -= crossTruck[1];
					endCount++;
				}
			}

			if (!q.isEmpty()) {
				int truck = q.peek();
				if (nowWeight + truck <= L) {
					q.poll();
					crossQueue.offer(new int[] { time + w, truck });
					nowWeight += truck;
				}
			}

			time++;
		}
		System.out.println(time);
	}
}
