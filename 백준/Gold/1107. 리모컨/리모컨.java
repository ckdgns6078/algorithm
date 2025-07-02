import java.util.*;

public class Main{

	static int[] arr;
	static List<Integer> list = new ArrayList();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int channel = sc.nextInt();
		int N = sc.nextInt();

		arr = new int[10 - N];

		boolean[] visited = new boolean[10];

		for (int i = 0; i < N; i++) {
			int input = sc.nextInt();
			visited[input] = true;
		}

		int idx = 0;
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				arr[idx] = i;
				idx++;
			}
		}

		int answer = 0;
		// 버튼을 눌러서 가는 경우
		answer = Math.abs(channel - 100);

		// 중복 순열을 통해서 가는 경우

		for (int i = 1; i <= 6; i++) {
			per(0, i, "");
		}

		for (int i = 0; i < list.size(); i++) {
			int num = list.get(i);
			int temp = String.valueOf(num).length() + Math.abs(channel - num);
			answer = Math.min(answer, temp);
		}
		System.out.println(answer);
	}

	static void per(int depth, int targetLen, String current) {
		if (depth == targetLen) {
			if (current.length() == 1 || current.charAt(0) != '0') {
				list.add(Integer.parseInt(current));
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			per(depth + 1, targetLen, current + arr[i]);
		}
	}

}
