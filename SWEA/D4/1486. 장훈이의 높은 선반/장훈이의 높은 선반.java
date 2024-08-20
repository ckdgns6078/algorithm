import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	static int N;
	static int B;
	static int[] arr;
	static int min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			N = sc.nextInt();
			B = sc.nextInt();
			arr = new int[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			subset(0 , 0 , new boolean[N]);
			
			System.out.println("#" + tc + " " + min);
			
			
			
			
		}

	}
	
	private static void subset(int idx, int k, boolean[] sel) {
		// basis part (반복의 횟수)
		if (idx == arr.length) {
			ArrayList<Integer> list = new ArrayList();
			for (int i = 0; i < sel.length; i++) {
				if(sel[i] == true) {
//					System.out.print(arr[i]+" ");
					list.add(i);
				}
			}
			top(list);
			return;
		}
		
		// inductive part(하나의 반복의 순간에 발생할수 있는 모든 경우)
		// 선택하는 경우
		sel[idx] = true;
		subset(idx + 1, k + 1,sel);
		// 선택하지 안는 경우
		sel[idx] = false;
		subset(idx + 1, k,sel);
	}
	
	
	private static void top(ArrayList<Integer> list) {
		int sum = 0;
		for( int i  = 0 ; i < list.size() ; i++) {
			sum+= arr[list.get(i)];
		}
		if( sum >=B) {
			min = Math.min(min, sum-B);
		}
		
	}
}
