import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next(); 
		int idx = 0;              
		long cnt = 0;             

		while (idx < input.length()) {
			cnt++;
			String s = String.valueOf(cnt);

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == input.charAt(idx)) {
					idx++;
					if (idx == input.length()) break;
				}
			}
		}

		System.out.println(cnt);
	}
}
