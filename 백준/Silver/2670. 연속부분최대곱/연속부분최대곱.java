

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		double[] arr = new double[N];
		double answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextDouble();
			answer = Math.max(arr[i], answer);
		}
		
		for(int i=0;i<N;i++) {
			double temp = arr[i];
			for(int j=i+1;j<N;j++) {
				temp *= arr[j];
				answer = Math.max(answer, temp);
			}
		}
		
		System.out.printf("%.3f\n", answer);
	}
}
