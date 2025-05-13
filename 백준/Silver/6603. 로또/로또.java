import java.util.*;

public class Main {

	static int n;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
			n = sc.nextInt();
			if(n == 0) {
				break;
			}
			arr = new int[n];
			
			for(int i=0;i<n;i++) {
				arr[i] = sc.nextInt();
			}
			
			comb(new ArrayList(),0);
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}
	
	public static void comb(List<Integer> temp , int cnt) {
		if(temp.size()==6) {
			for(int i=0;i<temp.size();i++) {
				sb.append(temp.get(i)+" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for(int i=cnt ; i<arr.length;i++) {
			temp.add(arr[i]);
			comb(temp , i+1);
			temp.remove(temp.size()-1);
		}
		
	}
}

