import java.util.*;

public class Main {

	static char[][] map;
	static int N;
	static int M;
	static List<Integer> order;
	static boolean resultCheck;
	static int[] jongsu;
	static int[] dr = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dc = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	static Set<Integer> aduinoSet;
	static Map<Integer, int[]> aduino;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		aduinoSet = new HashSet();
		aduino = new HashMap();

		jongsu = new int[2];
		map = new char[N][M];
		int temp = 1;
		for (int i = 0; i < N; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'I') {
					jongsu[0] = i;
					jongsu[1] = j;
				} else if (map[i][j] == 'R') {
					aduinoSet.add(temp);
					aduino.put(temp, new int[] { i, j });
					temp++;
				}
			}
		}

		resultCheck = true;
		int resultIdx = 0;
		// 오더 입력값
		order = new ArrayList();
		String orderInput = sc.next();
		for (int i = 0; i < orderInput.length(); i++) {
			order.add(orderInput.charAt(i) - '0');
		}

		for (int i = 0; i < order.size(); i++) {
			int nowOrder = order.get(i); // 현재 이동하는 곳

			// 1. 종수가 이동을 한다.
			jongsuMove(nowOrder);
			// 2. 종수가 아두이노가 있는 곳으로 이동하면 게임이 끝나고 종수는 게임을 진다.
			if (!jongsuCheck()) {
				resultCheck = false;
				resultIdx = i;
				break;
			}
			// 8가지 방향중 가장 작아지는 방향으로 이동한다.
			Map<String, List<Integer>> moveAduino = aduinoMove();
			// 미친 아두이노가 종수랑 똑같은 칸에 있게 되면 게임이 종료된다.
			if (!jongsuCheck()) {
				resultCheck = false;
				resultIdx = i;
				break;
			}
			// 2개 이상 아두이도가 있을 경우 아두이노는 삭제된다.
			aduinoCheck(moveAduino);

			map[jongsu[0]][jongsu[1]] = 'I';

			for (int key : aduino.keySet()) {
				int[] adu = aduino.get(key);
				map[adu[0]][adu[1]] = 'R';
			}
		}

		if (resultCheck) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("kraj " + (resultIdx + 1));
		}

	}

	public static void jongsuMove(int nowOrder) {
		map[jongsu[0]][jongsu[1]] = '.';
		jongsu[0] += dr[nowOrder];
		jongsu[1] += dc[nowOrder];
	}

	public static boolean jongsuCheck() {
		int jr = jongsu[0];
		int jc = jongsu[1];

		for (int idx : aduinoSet) {
			int[] adu = aduino.get(idx);
			if (jr == adu[0] && jc == adu[1]) {
				return false;
			}
		}
		return true;
	}

	public static Map<String, List<Integer>> aduinoMove() {
		Map<String, List<Integer>> moveAduino = new HashMap();

		for (int idx : aduinoSet) {
			int[] adu = aduino.get(idx);

			map[adu[0]][adu[1]] = '.';
			int moveTemp = Integer.MAX_VALUE;
			int moveDir = Integer.MAX_VALUE;
			for (int i = 1; i < 10; i++) {
				int nr = adu[0] + dr[i];
				int nc = adu[1] + dc[i];

				int sum = Math.abs(jongsu[0] - nr) + Math.abs(jongsu[1] - nc);
				if (sum < moveTemp) {
					moveTemp = sum;
					moveDir = i;
				}
			}
			int smallR = adu[0] + dr[moveDir];
			int smallC = adu[1] + dc[moveDir];
			aduino.get(idx)[0] = smallR;
			aduino.get(idx)[1] = smallC;

			String temp = String.valueOf(smallR) +"," +String.valueOf(smallC);
			if (moveAduino.containsKey(temp)) {
				moveAduino.get(temp).add(idx);
			} else {
				List<Integer> list = new ArrayList();
				list.add(idx);
				moveAduino.put(temp, list);
			}
		}
		return moveAduino;
	}

	public static void aduinoCheck(Map<String, List<Integer>> moveAduino) {
		for (String str : moveAduino.keySet()) {
			List<Integer> list = moveAduino.get(str);
			if (list.size() >= 2) {
				for (int i = 0; i < list.size(); i++) {
					int idx = list.get(i);
					aduinoSet.remove(idx);
					aduino.remove(idx);
				}
			}
		}
	}

}
