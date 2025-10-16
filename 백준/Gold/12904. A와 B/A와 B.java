import java.util.*;

public class Main {

	static String S;
	static String T;
	static boolean check = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		S = sc.next();
		T = sc.next();

		run(T);
		System.out.println(check ? 1 : 0);

	}

	public static void run(String word) {

		if (S.equals(word)) {
			check = true;
			return;
		}

		if (word.equals("") || check) {
			return;
		}

		boolean temp = lastWordCheck(word);
		String str;
		if (temp) {
			str = deleteA(word);
		} else {
			str = reverse(word);
		}
		run(str);

	}

	public static String deleteA(String word) {

		return word.substring(0, word.length() - 1);
	}

	public static String reverse(String word) {
		String str = word.substring(0, word.length() - 1);
		StringBuffer sb = new StringBuffer(str);

		return sb.reverse().toString();
	}

	public static boolean lastWordCheck(String word) {
		// A이면 true;
		char c = word.charAt(word.length() - 1);
		if (c == 'A') {
			return true;
		}
		// B이면 false
		return false;
	}

}
