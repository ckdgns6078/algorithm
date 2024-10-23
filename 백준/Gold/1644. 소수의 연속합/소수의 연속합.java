import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		ArrayList<Integer> list = findPrimes(n);
		int result = 0;
		int start =0;
		int end =0;
		int sum=0;
		
		
		 while (true) {
	            if (sum >= n) sum -= list.get(start++);
	            else if (end == list.size()) break;
	            else sum += list.get(end++);
	            if (n == sum) result++;
	        }
	        System.out.println(result);
	}

	public static ArrayList<Integer> findPrimes(int cnt) {
		int[] primes = new int[cnt + 1];
		int idx = 0;
		for (int i = 2; i < cnt + 1; i++) {
			primes[i] = i;
		}
		
		for (int i = 2; i < n + 1; i++) {
			if (primes[i] == 0) {
				continue;
			}

			for (int j = i * 2; j < n + 1; j += i) {
				primes[j] = 0;
			}
		}
		ArrayList<Integer> resultList = new ArrayList();
		for (int i = 0; i < cnt + 1; i++) {
			if (primes[i] != 0) {
				resultList.add(primes[i]);
			}
		}
		return resultList;
	}

}
