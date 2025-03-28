import java.util.*;

public class Main {

	static int N;
	static int newScore;
	static int P;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		newScore = sc.nextInt();
		P = sc.nextInt();
	
		list = new ArrayList();
		for(int i=0;i<N;i++) {
			int cnt = sc.nextInt();
			list.add(cnt);
		}
		list.add(newScore);
		
		Collections.sort(list , Collections.reverseOrder());

		int ranking = 1;
		int temp = 0;
		for(int i=0 ; i <list.size();i++) {
			int cnt = list.get(i);
			
			if(cnt > newScore) {
				ranking++;
				temp++;
			}else if(cnt == newScore) {
				temp++;
			}else {
				break;				
			}
		}
		System.out.println(P <temp ? -1 : ranking);
	}
	
}
