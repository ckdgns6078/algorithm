import java.util.*;

public class Main {
	static int N;
	static int K;
	static int[] arr;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		arr = new int[N];

		String input = sc.next();
		for (int i = 0; i < N; i++) {
			char c = input.charAt(i);
			if (c == 'H') {
				arr[i] = 0;
			} else {
				arr[i] = 1;
			}
		}

		// 사람이면 1 햄버거면 0

		for (int i = 0; i < N; i++) {
			if (arr[i] == 1) {

				// 앞에 검사
				int frontCnt = Integer.MAX_VALUE;
				boolean frontCheck = false;
				for (int j = i; j >= i - K; j--) {
					if(j<0) {
						break;
					}
					if (arr[j] == 0) {
						frontCnt = Math.min(frontCnt, j);
					}
				}
				if (frontCnt != Integer.MAX_VALUE) {
					arr[frontCnt] = 9;
					answer++;
					frontCheck = true;
				}

				// 뒤체크
				if (!frontCheck) {
					for (int j = i; j <= i + K; j++) {
						if (j >= N) {
							break;
						}
						if (arr[j] == 0) {
							answer++;
							arr[j] = 9;
							break;
						}

					}

				}

			}
		}
//		System.out.println(Arrays.toString(arr));
		System.out.println(answer);
	}
}
