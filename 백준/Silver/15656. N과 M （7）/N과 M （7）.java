import java.util.*;


public class Main {

	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);
		
		rec(new ArrayList());
		System.out.println(sb.toString());
	}
	
	public static void rec(List<Integer> list) {
		if(list.size()==m) {
			
			for( int i : list) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for(int i=0;i<arr.length;i++) {
			list.add(arr[i]);
			rec(list);
			list.remove(list.size()-1);
		}
		
		
	}

}
