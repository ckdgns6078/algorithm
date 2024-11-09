import java.util.*;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[][] checkMap;
	static int cnt = 1;
	static int temp = 0;
	static int answer = 0;

	/*
	 * char에 들어있는 값으로 이동해야한다. D , U , L , R
	 */
	static Map<Character, int[]> direction = new HashMap();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new char[n][m];
		checkMap = new int[n][m];
		direction.put('U', new int[] { -1, 0 });
		direction.put('D', new int[] { 1, 0 });
		direction.put('L', new int[] { 0, -1 });
		direction.put('R', new int[] { 0, 1 });

		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (checkMap[i][j] == 0) {
					temp = cnt;
					dfs(i, j);
					if(cnt == checkMap[i][j]) {
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt - 1);

	}

	private static void dfs(int r, int c) {
		if (checkMap[r][c] != 0) {
			temp = checkMap[r][c];
			
			return;
		}

		checkMap[r][c] = temp;
		dfs(r + direction.get(map[r][c])[0], c + direction.get(map[r][c])[1]);
		checkMap[r][c] = temp;

	}

}
