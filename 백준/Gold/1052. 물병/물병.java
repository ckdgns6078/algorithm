import java.util.*;

public class Main {

	static int N;
	static int K;
	static int answer =0;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		while(true) {
			
			String binary = Integer.toBinaryString(N);
			
			int left = 0;
			for(int i=0;i<binary.length();i++) {
				char c = binary.charAt(i);
				if(c =='1') {
					left++;
				}
				if(left>K) {
					break;
				}
			}
			
			
			if(left <=K) {
				break;
			}
			N++;
			answer++;
		}
		
		System.out.println(answer);
	}
}
