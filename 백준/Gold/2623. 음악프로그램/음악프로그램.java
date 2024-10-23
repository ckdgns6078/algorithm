import java.util.*;

public class Main {
	static int n;
	static int m;
	static ArrayList<Integer>[] list;
	static int[] table;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt(); // 가수의 수
		m = sc.nextInt(); // PD의 수

		table = new int[n + 1];
		list = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList();
		}

		/*
		 * 입력을 받고 list에 데이터를 어떻게 넣는가? 이전의 값들은 어떻게 저장을 하는가?
		 * 
		 */
		for (int i = 0; i < m; i++) {
			int size = sc.nextInt();
			int temp = 0;
			for (int j = 0; j < size; j++) {
				int input = sc.nextInt();
				if (temp != 0) {
					list[temp].add(input);
					table[input]++;
				}
				temp = input;
			}
		}

		ArrayList<Integer> resultList = new ArrayList();
		Queue<Integer> q = new ArrayDeque();

		for (int i = 1; i < n + 1; i++) {
			if (table[i] == 0) {
				q.offer(i);
			}
		}

		boolean check = true;

		StringBuilder sb = new StringBuilder();
		// q에 0으로 시작하는 값이 없을 경우 사이클이 있음으로 실행할 수 없음
		L: while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node);
			sb.append("\n");

			for (int i = 0; i < list[node].size(); i++) {
				int idx = list[node].get(i);
				table[idx]--;
				if (table[idx] == 0) {
					q.offer(idx);

				} else if (table[idx] < 0) {
					check = false;
					break L;
				}
			}

		}
		if(check) {
			for(int i=0;i<table.length ; i++) {
				if(table[i]!=0) {
					check=false;
					break;
				}
			}
		}
		
		if(check) {
			System.out.println(sb);			
		}else {
			System.out.println(0);
		}

	}

}
