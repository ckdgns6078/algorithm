import java.util.*;

public class Main {

	static int N;
	static int M;
	static Map<String,Integer> memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		
		memo = new HashMap();
		
		for(int i=0;i<N;i++) {
			String value = sc.next();
			memo.put(value, 0);
		}
			
		for(int i=0;i<M;i++) {
			String input = sc.next();
			String[] inputArr = input.split(",");
			for(int j=0;j<inputArr.length;j++) {
				memo.remove(inputArr[j]);
			}
			sb.append(memo.size()).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
