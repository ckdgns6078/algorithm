import java.util.*;
public class Solution {
	static String input;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for( int tc = 1 ;  tc<=T ;  tc++) {
			
			
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			int result =0;
			input = sc.next();
			Set<Integer> set = new TreeSet<Integer>();

			//값을 바꾼다.
			//treeSet에 저장한다.
			//값을 옮긴다.
			int cntSize = n/4;
			
			for( int i = 0 ; i < cntSize ; i++) {
				for( int j = 0 ; j < n ; j+=cntSize) {
					String cut = input.substring(j , j+cntSize);
					set.add(Integer.parseInt(cut , 16));
				}
				changeString();
				
			}
			
			int cnt =0;
			int resultCnt = set.size()-m;
			for( int data : set) {
				if(cnt == resultCnt) {
					result =data;
					break;
				}
				cnt++;
			}
			
			System.out.println("#" + tc +" " + result);
			
		}

	}
	private static void changeString() {
		char lastChar = input.charAt(input.length()-1);
		String rename = input.substring(0, input.length()-1);
		input = lastChar+rename;
		
		
	}

}
