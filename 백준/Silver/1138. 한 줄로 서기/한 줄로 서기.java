import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static List<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N + 1];
		list = new ArrayList();

		for (int i = 1; i < N + 1; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = N; i > 0; i--) {
			if (list.size() == 0) {
				list.add(i);
			} else if (list.size() == arr[i]) {
				list.add(i);
			} else if (list.size() > arr[i]) {
				list.add(arr[i], i);
			}
		}
		
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
