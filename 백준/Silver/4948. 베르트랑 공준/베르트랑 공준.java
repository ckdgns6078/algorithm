import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList();
		StringBuilder sb = new StringBuilder();
		int max = 0;
		while(true) {
			int n = sc.nextInt();
			if(n==0) {
				break;
			}
			max = Math.max(max, n);
			list.add(n);
		}
		
		int[] arr =new int[max*2+1];

		for(int i=2;i<arr.length ; i++) {
			for(int j=i+i ; j<arr.length ; j+=i) {
				if(arr[j]==0) {
					arr[j] = 1;
				}
			
			}
		}
		
		for(int i=0;i<list.size();i++) {
			int temp = list.get(i);
			
			int check =0;
			for(int j=temp+1 ; j<=temp*2;j++) {
				if(arr[j]==0) {
					check++;
				}
			}
			sb.append(check);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
