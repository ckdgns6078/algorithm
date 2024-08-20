import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int result =-1;
		
		int coin5 = N / 5;
		
		
		for( int i = coin5 ; i >=0 ; i--) {
			int check = N - (5 * i);
			
			if( check %3 == 0) {
				result = i + (check/3);
				break;
			}			
		}
		System.out.println(result);
		
		
	}

}
