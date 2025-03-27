import java.util.*;

public class Main {

	static int N;
	static String M;
	static Set<String> set;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.next();
		set = new HashSet();

		for (int i = 0; i < N; i++) {
			String input = sc.next();
			set.add(input);
		}

		switch (M) {
		case "Y": // 윷놀이 2
			answer = games(2);
			break;
		case "F": // 같은그림찾기 3
			answer = games(3);
			break;
		case "O": // 원카드 4
			answer = games(4);
		}
		System.out.println(answer);
	}

	public static int games(int cnt) {
		int size = set.size();
		if (cnt == 2) {
			return size;
		} else if (cnt == 3) {
			return size / 2;
		} else {
			return size / 3;
		}
	}

}
