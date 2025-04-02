import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static long answer = 0;
	static long temp = 0;
	static int check = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//입력부
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 초기값
		for (int i = 0; i < M; i++) {
			temp += arr[i];
		}
		answer = temp;
		check = 1;

		//누적합 , 슬라이딩윈도우
		for (int i = 1; i <= N - M; i++) {
			temp = temp - arr[i - 1] + arr[i + M - 1];

			if (temp > answer) {
				answer = temp;
				check = 1;
			} else if (temp == answer) {
				check++;
			}
		}

        //출력부
		if (answer != 0) {
			System.out.println(answer);
			System.out.println(check);
		} else {
			System.out.println("SAD");
		}
	}
}
