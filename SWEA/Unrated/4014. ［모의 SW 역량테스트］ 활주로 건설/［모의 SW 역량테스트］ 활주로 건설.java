import java.io.*;
import java.util.*;

public class Solution {

	static int map[][]; // 지형
	static boolean v[][]; // 경사로 설치 여부 확인
	static int answer;
	static int X, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 첫 번째 줄
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 활주로 크기
			X = Integer.parseInt(st.nextToken()); // 경사로 길이

			map = new int[N][N];
			answer = 0;

			// 나머지 줄
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			print(map);

			// 경사로 가로 체크
			v = new boolean[N][N];
			rowCheck();

			// 경사로 세로 체크
			v = new boolean[N][N];
			colCheck();

			System.out.println("#" + test_case + " " + answer);
		} // [E] test_case
	}

	private static void colCheck() {
		// 세로 한 줄씩 체크
		for (int j = 0; j < map[0].length; j++) {
			// 위쪽부터 체크
			L: for (int i = 0; i < map.length; i++) {
				if (i == map.length - 1) {
//					System.out.println(j + "열");
					answer++;
					break;
				}
				// 경사로 길이가 같다면
				if (map[i][j] == map[i + 1][j]) {
					continue;
				}
				// 자신보다 아래쪽의 높이가 한 칸 더 크다면
				else if (map[i][j] == map[i + 1][j] - 1) {
					// 경사로의 길이보다 왼쪽에 남은 길이가 더 짧다면
					if (X - 1 > i) {
						break L; // 해당 줄은 활주로 건설이 불가능함
					}

					for (int k = i; k > i - X; k--) {
						// 경사로를 건설한 적이 없었다면
						if (v[k][j] != true) {
							v[k][j] = true;
						}
						// 경사로를 건설한 적이 있었다면
						else {
							break L; // 해당 줄은 활주로 건설이 불가능함
						}
					}
				}

				// 자신보다 아래쪽의 높이가 한 칸 더 작다면
				else if (map[i][j] == map[i + 1][j] + 1) {
					// 경사로의 길이보다 오른쪽에 남은 길이가 더 짧다면
					if (X > N - i - 1) {
						break L; // 해당 줄은 활주로 건설이 불가능함
					}

					// 경사로를 건설할 개수만큼 반복
					for (int k = i + 1; k <= i + X; k++) {
						// 높낮이가 일정하다면
						if (map[k][j] == map[i][j] - 1) {
							// 경사로를 건설한 적이 없었다면
							if (v[k][j] != true) {
								v[k][j] = true;
							}
							// 경사로를 건설한 적이 있었다면
							else {
								break L; // 해당 줄은 활주로 건설이 불가능함
							}
						}
						// 높낮이가 다르다면
						else {
							break L; // 해당 줄은 활주로 건설이 불가능함
						}

					}
				}

				// 자신과 두 칸이상 높이가 차이가 난다면
				else {
					break L; // 해당 줄은 활주로 건설이 불가능함
				}

			} // [E] 한 줄내 비교
		}
	}

	private static void rowCheck() {
		// 한 줄씩 체크
		for (int i = 0; i < map.length; i++) {
			// 왼쪽부터 체크
			L: for (int j = 0; j < map[i].length; j++) {
				if (j == map[i].length - 1) {
//					System.out.println(i + "행");
					answer++;
					break;
				}

				// 경사로 길이가 같다면
				if (map[i][j] == map[i][j + 1]) {
					continue;
				}
				// 자신보다 오른쪽의 높이가 한 칸 더 크다면
				else if (map[i][j] == map[i][j + 1] - 1) {
					// 경사로의 길이보다 왼쪽에 남은 길이가 더 짧다면
					if (X - 1 > j) {
						break L; // 해당 줄은 활주로 건설이 불가능함
					}

					for (int k = j; k > j - X; k--) {
						// 경사로를 건설한 적이 없었다면
						if (v[i][k] != true) {
							v[i][k] = true;
						}
						// 경사로를 건설한 적이 있었다면
						else {
							break L; // 해당 줄은 활주로 건설이 불가능함
						}
					}
				}

				// 자신보다 오른쪽의 높이가 한 칸 더 작다면
				else if (map[i][j] == map[i][j + 1] + 1) {
					// 경사로의 길이보다 오른쪽에 남은 길이가 더 짧다면
					if (X > N - j - 1) {
						break L; // 해당 줄은 활주로 건설이 불가능함
					}

					// 경사로를 건설할 개수만큼 반복
					for (int k = j + 1; k <= j + X; k++) {
						// 높낮이가 일정하다면
						if (map[i][k] == map[i][j + 1]) {
							// 경사로를 건설한 적이 없었다면
							if (v[i][k] != true) {
								v[i][k] = true;
							}
							// 경사로를 건설한 적이 있었다면
							else {
								break L; // 해당 줄은 활주로 건설이 불가능함
							}
						}
						// 높낮이가 다르다면
						else {
							break L; // 해당 줄은 활주로 건설이 불가능함
						}

					}
				}

				// 자신과 두 칸이상 높이가 차이가 난다면
				else {
					break L; // 해당 줄은 활주로 건설이 불가능함
				}

			} // [E] 한 줄내 비교

		}
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}