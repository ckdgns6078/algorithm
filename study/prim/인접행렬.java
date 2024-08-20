package graph;

import java.util.Arrays;
import java.util.Scanner;

public class Prim인접행렬 {
	static int V, E;
	static int[][] adj;
	public static void main(String[] args) {
//		System.setIn(Prim.class.getResourceAsStream(""));
		
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		adj = new int[V][V];
		
		//MST는 무방향 그래프이다.
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			adj[a][b]=c;
			adj[b][a]=c;
		}
		
		//정점의 거리배열이 필요하다.
		int[] dist = new int[V];
		// 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		//기준 정점 역할 배열
		boolean[] v = new boolean[V];
		
		//prim
		// 임의의 기준정점을 선택한다 ( 보통 0번 선택 )
		dist[0] =0;
		
		
		//V-1 번 만큼 거리배열 업데이트한다.
		for( int cnt = 0 ; cnt <V-1 ; cnt++) {
			//거리배열에서 기준정점 역할을 하지 않은 정점중 최소 거리값을 가지고 있는 정점을 찾는다.
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;
			
			for( int i = 0 ; i < V ; i++) {
				if(dist[i]<minD && !v[i]) {
					minIdx = i;
					minD = dist[i];
				}
			}
			// 기준 정점을 찾았다.
			v[minIdx] = true;
			
			//기준정과 연결된 간선의 값을 거리배열에 update 한다.
			//단 , 기존값보다 작다면
			for( int i = 0 ; i < V ;i++) {
				if(adj[minIdx][i]!=0 && v[i] && adj[minIdx][i] < dist[i]) {
					dist[i] = adj[minIdx][i];
				}
			}
		}
		
		
		System.out.println(Arrays.toString(dist));
		
		

	}

}
