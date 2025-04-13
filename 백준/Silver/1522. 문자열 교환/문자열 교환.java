import java.util.*;

public class Main {

	static String str;
	static int aCnt = 0;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		str = sc.next();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				aCnt++;
			}
		}

		for (int i = 0; i < str.length(); i++) {
			int temp = 0;

			for (int j = i; j < i + aCnt; j++) {
				int idx = j;
				if (idx >= str.length()) {
					idx = j - str.length();
				}

				char c = str.charAt(idx);
				if (c == 'b') {
					temp++;
				}
			}
			answer = Math.min(answer, temp);

		}

		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);

	}
}
