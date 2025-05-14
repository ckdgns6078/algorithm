import java.util.*;

public class Main {

	
	static List<Integer> list = new ArrayList();
	static int N;
	static int[] arr;
	static int answer =1;
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		N = sc.nextInt();
		arr = new int[N+1];
		
		for(int i=1;i<N+1;i++) {
			arr[i] = sc.nextInt();
		}
		
		list.add(arr[1]);
		for(int i=1; i < N+1;i++) {
			lts(arr[i]);
		}
		
		answer = N - list.size();
		System.out.println(answer);
	}
	
	
	public static void lts(int cnt){
		int left = 0;
		int right = list.size()-1;
		
		if(cnt > list.get(right)){
			list.add(cnt);
			return;
		}
		
		
		while(left < right) {
		
			int mid = (left + right) / 2;
			
			if(list.get(mid)<cnt) { // 비교하는 값이 작을 경우
				left = mid+1;
			}else {					// 비교 하는 값이 클 경우
				right = mid;
			}
			
		}
		list.set(right, cnt);
	}
}
