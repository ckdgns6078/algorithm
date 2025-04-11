import java.util.*;

public class Main {

	static int N;
	static int end;
	static Map<Integer, List<int[]>> shortCut;
	static int[] dp;

	public static void main(String[] args) {
		// 초기값 설정
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		end = sc.nextInt();

		shortCut = new HashMap();
		dp = new int[end + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		// 입력부
		for (int i = 0; i < N; i++) {
			int startPoint = sc.nextInt();
			int endPoint = sc.nextInt();
			int length = sc.nextInt();

			if (endPoint <= end) {
				// 값이 있으면 add를 해야하고
				if (shortCut.containsKey(startPoint)) {
					shortCut.get(startPoint).add(new int[] { endPoint, length });
				} else {// 값이 없으면 new를 해야한다.
					List<int[]> list = new ArrayList();
					list.add(new int[] { endPoint, length });
					shortCut.put(startPoint, list);
				}
			}

		}

		dp[0] = 0;

		// solution
		for (int i = 0; i <= end; i++) {
			if (i != 0) {
				dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
			}

			if (shortCut.containsKey(i)) {
				for (int[] shortC : shortCut.get(i)) {
					int endPoint = shortC[0];
					int pay = shortC[1];
					dp[endPoint] = Math.min(dp[endPoint], dp[i] + pay);
				}
			}
		}

//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[end]);
	}
}
