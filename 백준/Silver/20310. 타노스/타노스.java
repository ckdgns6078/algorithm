import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		int size = str.length();
		int zeroCnt = 0;
		int oneCnt = 0;

		for (int i = 0; i < size; i++) {
			int temp = str.charAt(i) - '0';

			if (temp == 0) {
				zeroCnt++;
			} else {
				oneCnt++;
			}
		}
		zeroCnt /= 2;
		oneCnt /= 2;

		String one = "";
		for (int i = 0; i < size; i++) {
			char c = str.charAt(i);
			if (c == '1' && oneCnt > 0) {
				oneCnt--;
				continue;
			}
			one += c;
		}

		String answer = "";
		for (int i = one.length() - 1; i >= 0; i--) {
			char c = one.charAt(i);

			if (c == '0' && zeroCnt > 0) {
				zeroCnt--;
				continue;
			}
			answer = c + answer;
		}

		System.out.println(answer);
	}
}
