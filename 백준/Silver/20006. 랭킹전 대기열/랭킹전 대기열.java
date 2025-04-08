import java.util.*;

public class Main {
	static int playerCnt;
	static int roomCnt;
	static Map<String, Integer> playerLevelMap;
	static Map<Integer, Integer> roomLevel;

	static ArrayList<String[]> rooms = new ArrayList();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		playerCnt = sc.nextInt();
		roomCnt = sc.nextInt();
		playerLevelMap = new HashMap();
		roomLevel = new HashMap();

		for (int i = 0; i < playerCnt; i++) {
			int level = sc.nextInt();
			String name = sc.next();

			playerLevelMap.put(name, level);
			findRoom(level, name);

		}

		for (int i = 0; i < rooms.size(); i++) {
			sb.append(statusCheck(i)).append("\n");
		
			Arrays.sort(rooms.get(i), Comparator.nullsLast(String::compareTo));
			for(int j=0;j<roomCnt;j++) {
				if(rooms.get(i)[j]==null) {
					break;
				}
				sb.append(playerLevelMap.get(rooms.get(i)[j])).append(" ").append(rooms.get(i)[j]).append("\n");
			}
		}
		System.out.println(sb.toString());

	}

	public static String statusCheck(int idx) {
		for (int i = 0; i < rooms.get(idx).length; i++) {
			if(rooms.get(idx)[i]==null) {
				return "Waiting!"; 
			}
		}
		return "Started!";
	}

	public static void findRoom(int level, String name) {

		for (int i = 0; i < rooms.size(); i++) {

			int roomLev = roomLevel.get(i);
			if (level >= roomLev - 10 && level <= roomLev + 10) {
				for (int j = 0; j < roomCnt; j++) {
					if (rooms.get(i)[j] == null) {
						rooms.get(i)[j] = name;
						return;
					}
				}
			}
		}

		// 방이 없을 경우 신규로 만들어서 넣는다.
		String[] arr = new String[roomCnt];
		arr[0] = name;
		rooms.add(arr);
		int temp = rooms.size() - 1;
		roomLevel.put(temp, level);
	}

}
