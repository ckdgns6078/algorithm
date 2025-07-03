
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int L;
	static int K;
	static List<int[]> list;

	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();
		K = sc.nextInt();

		list = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			list.add(new int[] { r, c });
		}

		answer = K;

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int lr = list.get(i)[0];
				int lc = list.get(j)[1];

				check(lr, lc);
			}
		}

		System.out.println(answer);
	}

	public static void check(int r, int c) {
		int br = r + L;
		int bc = c + L;

		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			int[] arr = list.get(i);
			int starR = arr[0];
			int starC = arr[1];

			if (r <= starR && starR <= br && c <= starC && starC <= bc) {
				sum++;
			}
		}

		answer = Math.min(answer, K - sum);
	}
}
