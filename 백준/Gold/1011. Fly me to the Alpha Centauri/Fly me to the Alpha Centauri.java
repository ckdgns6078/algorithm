import java.util.*;

public class Main {
	static int n;
	static int[][] order;
	static int[] move;
	static int max =0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = sc.nextInt();
		for(int i=0;i<n;i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int answer =0;
			
			int dist = end-start;
			int temp = (int)Math.sqrt(dist);
			
			if(temp == Math.sqrt(dist)) {
				answer = temp*2-1;
				
			}else if(dist-temp*temp<=temp) {
				answer = temp*2;
			}else {
				answer = temp*2+1;
			}
			
			sb.append(answer).append("\n");

		}
		System.out.println(sb);
	
	
	}
	/*
	 * 
	 * 1	1		1
		2	11		2		
		3	111		3
		4	121		3
		5	1211		4
		6	1221		4
		7	12211		5
		8	12221		5
		9	12321		5
		10	123211		6
		11	123221		6
		12	123321		6
		1233211		7
		14	1233221		7
		15	1233321		7
		16	1234321		7
		17	12343211		8
		18	12343221		8
		19	12343321		8
		20	12344321		8
		21	123454321	9
	 * 
	 */
}
