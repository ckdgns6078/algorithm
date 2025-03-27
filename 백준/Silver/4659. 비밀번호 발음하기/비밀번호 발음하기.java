import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while (true) {
			String input = sc.next();
			boolean answerCheck = true;
			if (input.equals("end")) {
				break;
			}
			sb.append("<").append(input).append(">");
			if (!aeiou(input) || !doubleCheck(input) || !slidingWindow(input)) {
				sb.append(" is not acceptable.");
			} else {
				sb.append(" is acceptable.");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		/**
		 * 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다. 같은 글자가 연속적으로
		 * 두번 오면 안되나, ee 와 oo는 허용한다.
		 */
	}

	public static boolean doubleCheck(String str) {
		if (str.length() < 2) {
			return true;
		}

		for (int i = 0; i < str.length() - 1; i++) {
			char c = str.charAt(i);
			char c2 = str.charAt(i + 1);

			if (c == c2) {
				if (!(c == 'e' || c == 'o')) {
					return false;
				}
			}

		}
		return true;
	}

	public static boolean aeiou(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				return true;
			}
		}
		return false;
	}

	public static boolean slidingWindow(String input) {
		if (input.length() < 3)
			return true;

		for (int i = 0; i < input.length() - 2; i++) {
			boolean first = isVowel(input.charAt(i));
			boolean second = isVowel(input.charAt(i + 1));
			boolean third = isVowel(input.charAt(i + 2));

			if (first && second && third)
				return false; // 모음 3개
			if (!first && !second && !third)
				return false; // 자음 3개
		}
		return true;
	}

	public static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
