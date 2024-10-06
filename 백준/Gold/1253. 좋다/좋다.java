import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		long[] arr = new long[n];
		int result = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);
		// 다른 두 수의 합으로 만들어진다.
		for (int i = 0; i < n; i++) {
			long target = arr[i];

			int left = 0;
			int right = n - 1;

			while (left < right) {

				if (left == i) {
					left++;
					continue;
				}else if(right == i) {
					right--;
					continue;
				}

				long sum = arr[left] + arr[right];
				
				if(sum > arr[i]) {
					right--;
				}else if(sum < arr[i]) {
					left++;
				}else {
					result++;
					break;
				}

			}

		}

		System.out.println(result);

	}

}
