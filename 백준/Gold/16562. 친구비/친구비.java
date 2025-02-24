import java.util.*;

public class Main {
	public static int[] parent;
	public static int N, M, money;
	public static int needMoney = 0;
	public static int[] moneyArr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		money = sc.nextInt();

		moneyArr = new int[N];
		for (int i = 0; i < N; i++) {
			moneyArr[i] = sc.nextInt();
		}

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			union(x, y);
		}

		for (int i = 0; i < N; i++) {
			find(i);
		}
		
		
		int[] minCost = new int[N];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		
		for(int i=0;i<N;i++) {
			int root = find(i);
			minCost[root] = Math.min(minCost[root], moneyArr[i]);
		}
		
		for (int i = 0; i < N; i++) {
            if (minCost[i] != Integer.MAX_VALUE) { // 유효한 최소 비용만 더함
                needMoney += minCost[i];
            }
        }
		
		if(needMoney > money) {
			System.out.println("Oh no");
		}else {
			System.out.println(needMoney);
		}
		
		
		
		
	}

	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX != rootY) {
			if (moneyArr[rootX] < moneyArr[rootY]) {
				parent[rootY] = rootX;
			} else {
				parent[rootX] = rootY;
			}
		}
	}

	public static boolean isSame(int x, int y) {
		if (find(x) == find(y)) {
			return true;
		} else {
			return false;
		}
	}

}
