import java.util.*;

public class Main {

	static int[] arr;
	static ArrayList<Integer> list = new ArrayList();
	static int n;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = sc.nextInt();
		}
		list.add(arr[1]);
		for (int i = 1; i < n + 1; i++) {
			findLIS(arr[i]);
		}

		System.out.println(list.size());
	}

	private static void findLIS(int cnt) {
		int left = 0;
		int right = list.size() - 1;
		if (cnt > list.get(right)) {
			list.add(cnt);
		} else {
			while (left < right) {
				int center = (left + right) / 2;
				int middle = list.get(center);

				if (cnt > middle) {
					left = center + 1;
				} else {
					right = center;
				}
			}
			list.set(right, cnt);
		}
	}

}
