package test;

import java.util.*;

public class 텀프로젝트 {

	static int people;
	static int[] arr;
	static int answer;
	static boolean[] team;
	static boolean[] visited;
	static boolean backCheck;

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();
		for (int t = 0; t < TC; t++) {

			people = sc.nextInt();
			answer = people;
			arr = new int[people + 1];
			team = new boolean[people + 1];
			visited = new boolean[people + 1];
			backCheck = false;
			
			for (int i = 1; i < people + 1; i++) {
				arr[i] = sc.nextInt();
				if (i == arr[i]) {
					answer--;
					team[i] = true;
				}
			}

			for (int i = 1; i < people + 1; i++) {
				if (!team[i]) {
					backCheck = false;
					findTeam(i, arr[i], 1);
				}

			}

			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void findTeam(int start, int node, int result) {
		if(team[node]) {
			return;
		}
		
		if (node == start) {
			backCheck = true;
			team[start] = true;
			team[node] = true;
			answer -= result;
			return;
		}
		if (visited[node] || node == arr[node]) {
			return;
		}
		
		visited[node] = true;
		findTeam(start, arr[node], result + 1);
		visited[node] = false;

		if (!backCheck) {
			team[node] = false;
		}else {
			team[node] = true;
		}

	}
}
