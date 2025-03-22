import java.util.*;

public class Main {
	static int N;
	static ArrayList<Integer> aList;
	static ArrayList<Integer> bList;
	static int answer =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		aList = new ArrayList();
		bList = new ArrayList();
		
		N = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			int input = sc.nextInt();
			aList.add(input);
		}
		
		for(int i=0;i<N;i++) {
			int input = sc.nextInt();
			bList.add(input);
		}
		
		Collections.sort(aList);
		Collections.sort(bList , Collections.reverseOrder());
		
		for(int i=0;i<N;i++) {
			answer += aList.get(i) * bList.get(i);
		}
		
		System.out.println(answer);
		
	}
}
