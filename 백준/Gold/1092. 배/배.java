import java.util.*;


public class Main {

	static int N;
	static int M;
	static int[] crain;
	static ArrayList<Integer> box;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		crain = new int[N];
		for(int i=0;i<N;i++) {
			crain[i] = sc.nextInt();
		}
		
		M = sc.nextInt();
		box = new ArrayList();
		for(int i=0;i<M;i++) {
			int input = sc.nextInt();
			box.add(input);
		}
		
		Arrays.sort(crain);
		Collections.sort(box);
		
		
		int cnt = 0;
		int answer = 0;
		int last = crain.length-1;
		
		while(cnt < M) {
			
			boolean temp = false;
			for(int i=last ; i>=0 ; i--) {
				int idx = binarySearch(crain[i]);
				
				if(idx == -1) {
					continue;
				}
				
				box.remove(idx);
				cnt++;
				temp = true;
			}
			if(!temp) {
				answer = -1;
				break;				
			}
			answer++;
			
		}
		
		System.out.println(answer);
		
	
		
	}
	
	public static int binarySearch( int temp) {
		int left = 0;
		int right = box.size()-1;
		int result = -1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(box.get(mid)<=temp) {
				left = mid+1;
				result = mid;
			}else {
				right = mid -1;
			}
		}
		return result;
	}
}
