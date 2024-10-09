import java.util.*;

public class Main {
	static ArrayList<String> resultList;
	static int n;
	static int[] arr;
	static char[] operations = { '+', '-', ' ' };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			n = sc.nextInt();
			arr = new int[n];
			resultList = new ArrayList();
			
			for (int i = 0; i < n; i++) {
				arr[i] = i + 1;
			}
			backtracking(1, String.valueOf(arr[0]));
			
			Collections.sort(resultList);
			for (int i = 0; i < resultList.size(); i++) {
				sb.append(resultList.get(i));
				sb.append("\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void backtracking(int idx, String result) {
		if (idx >= n) {
			if (resultCheck(result) == 0) {
				resultList.add(result);
			}
			return;
		}

		for (int i = 0; i < 3; i++) {
			result += operations[i];
			result += String.valueOf(arr[idx]);

			backtracking(idx + 1, result);
			result = result.substring(0, result.length() - 2);
		}

	}

	private static int resultCheck(String result) {
		String change = result.replace(" ", "");
		String[] resultArr = change.split("\\+|\\-");
		int sum = Integer.parseInt(resultArr[0]);
		int check = 1;
		for (int i = 0; i < result.length(); i++) {
			char c = result.charAt(i);
			if (c == '+') {
				if(!resultArr[check].isEmpty()) {
					sum += Integer.parseInt(resultArr[check]);					
					check++;
				}
			} else if (c == '-') {
				if(!resultArr[check].isEmpty()) {					
					sum -= Integer.parseInt(resultArr[check]);
					check++;
				}
			}
		}
		return sum;

	}

}
