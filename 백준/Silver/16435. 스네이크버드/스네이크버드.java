import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int size = sc.nextInt();
		int[] fruit = new int[n];
		boolean[] check = new boolean[n];
		
		
		for( int i = 0 ; i < n ; i++) {
			fruit[i]= sc.nextInt();
		}
		Arrays.sort(fruit);
		
		for( int i = 0 ; i < n ; i++) {
			if( !check[i] && fruit[i] <=size) {
				size++;
				check[i] = true;
			}
			
		}
		System.out.println(size);
		
		
		
	}

}
