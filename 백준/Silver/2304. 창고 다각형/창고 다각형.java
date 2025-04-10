import java.util.*;

public class Main {
	static int N;
	static List<Integer> widthList;
	static Map<Integer, Integer> map;
	static List<Integer> biggestList;
	static int left = 0;
	static int right = 0;
	static int answer = 0;
	static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		widthList = new ArrayList();
		map = new HashMap();
		biggestList = new ArrayList();

		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int width = sc.nextInt();
			int height = sc.nextInt();
			max = Math.max(height, max);
			widthList.add(width);
			map.put(width, height);
		}

		Collections.sort(widthList);
		for (int i = 0; i < N; i++) {
			if (max == map.get(widthList.get(i))) {
				biggestList.add(i);
			}
		}

		left = biggestList.get(0);
		right = biggestList.get(biggestList.size() - 1);

		leftSide();
		rightSide();
		biggestSide();
		System.out.println(answer);

	}

	public static void leftSide() {
		int startX = widthList.get(0);
		int h = map.get(startX);
		for (int i = 1; i <= left; i++) {
			int currentX = widthList.get(i);
			int currentH = map.get(currentX);
			if (currentH > h) {
				answer += (currentX - startX) * h;
				startX = currentX;
				h = currentH;
			}
		}
	}

	public static void rightSide() {
		int startX = widthList.get(widthList.size() - 1);
		int h = map.get(startX);
		for (int i = widthList.size() - 2; i >= right; i--) {
			int currentX = widthList.get(i);
			int currentH = map.get(currentX);
			if (currentH > h) {
				answer += (startX - currentX) * h;
				startX = currentX;
				h = currentH;
			}
		}
	}

	public static void biggestSide() {
		int leftX = widthList.get(left);
		int rightX = widthList.get(right);
		answer += (rightX - leftX + 1) * max;
	}

}
