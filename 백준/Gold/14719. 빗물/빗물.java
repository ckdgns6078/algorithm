import java.util.*;

public class Main {
	static int h;
	static int w;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		h = sc.nextInt();
		w = sc.nextInt();
		arr = new int[w];
		for(int i = 0 ; i < w ; i++) {
			arr[i] = sc.nextInt();
		}
		boolean leftCheck = false;
		
		for(int i = 1 ; i <= h ; i++) {
			for(int j = 0 ; j < w ; j++) {
				
				if(!leftCheck && arr[j]>=i) {
					leftCheck = true;
				}
				if(arr[j]<i) {
					if(leftCheck && rightCheck(j , i)) {
						arr[j]++;
						answer++;
					}	
				}
			}
			leftCheck = false;
		}
//		System.out.println(Arrays.toString(arr));
		
		System.out.println(answer);
		
	}
	private static boolean leftCheck(int j , int height) {
		if(j-1>=0) {
			for(int i = j-1 ; i >=0 ; i--) {
				if(arr[i]>=height) {
					return true;
				}
			}
		}
		return false;
	}
	private static boolean rightCheck(int j , int height) {
		if(j+1 < w) {
			for(int i = j+1 ; i < w ; i++) {
				if(arr[i]>=height) {
					return true;
				}
			}
		}
		return false;
	}


}
