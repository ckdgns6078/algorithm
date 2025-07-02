import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		boolean answer = true;

		int cnt = 0;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == 'P') {
				cnt++;
			} else if (c == 'A') {
				if (cnt < 2) { // 앞에 pp가 없으면 안됨
					answer = false;
					break;
				}

				if (i + 1 >= input.length() || input.charAt(i + 1) != 'P') { // a다음으로 p가 있는지 검사
					answer = false;
					break;
				}

				cnt -= 2;

			}
		}

		System.out.println((answer == true && cnt == 1) ? "PPAP" : "NP");
	}

}
