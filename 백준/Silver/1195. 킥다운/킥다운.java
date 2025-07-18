

import java.util.*;

public class Main {

	static int[] gear1;
	static int[] gear2;
	static int answer = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String input = sc.next();
		String input2 = sc.next();

		createGear(input, input2);
		answer = input.length()+input2.length();
		for (int i = -gear2.length; i < gear1.length; i++) {
			if (check(i)) {
				int left = Math.min(0, i);
				int right = Math.max(gear1.length, i + gear2.length);
				int size = right - left;
				answer = size < answer ? size : answer;
			}
		}
		System.out.println(answer);
	}

	public static boolean check(int idx) {
		for (int i = 0; i < gear2.length; i++) {
			int temp = idx + i;
			if (temp < 0) {
				continue;
			}
			if (temp >= gear1.length) {
				break;
			}
			if (gear2[i] == 2 && gear1[temp] == 2) {
				return false;
			}
		}
		return true;
	}

	public static void createGear(String input1, String input2) {
		String temp;

		if (input2.length() > input1.length()) {
			temp = input1;
			input1 = input2;
			input2 = temp;
		}

		answer = input1.length();
		gear1 = new int[input1.length()];
		gear2 = new int[input2.length()];
		for (int i = 0; i < answer; i++) {
			gear1[i] = input1.charAt(i) - '0';

			if (i < gear2.length) {
				gear2[i] = input2.charAt(i) - '0';
			}
		}

	}

}
