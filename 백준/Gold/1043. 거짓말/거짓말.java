import java.util.*;

public class Main {
	static int people;
	static int party;
	static int knowCnt;
	static Set<Integer> set = new HashSet();
	static int[][] peopleList;
	static boolean[] check;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		people = sc.nextInt();
		party = sc.nextInt();
		knowCnt = sc.nextInt();
		peopleList = new int[party][];
		check = new boolean[party];
		result = party;
		for (int i = 0; i < knowCnt; i++) {
			set.add(sc.nextInt());
		}
		for (int i = 0; i < party; i++) {
			int peoples = sc.nextInt();
			peopleList[i] = new int[peoples];
			for (int j = 0; j < peoples; j++) {
				peopleList[i][j] = sc.nextInt();
			}
		}
		boolean checkEnd = false;
		while (!checkEnd) {
			checkEnd = true;
			for (int i = 0; i < peopleList.length; i++) {
				if (check[i] == false) {
					for (int j = 0; j < peopleList[i].length; j++) {
						if (set.contains(peopleList[i][j])) {
							addSet(i);
							check[i] = true;
							checkEnd = false;
							result--;
							break;
						}
					}
				}

			}

		}
		System.out.println(result);
	}

	private static void addSet(int cnt) {
		for (int i = 0; i < peopleList[cnt].length; i++) {
			set.add(peopleList[cnt][i]);
		}

	}

}
