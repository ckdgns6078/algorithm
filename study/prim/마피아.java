package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마피아 {

	static int n;
	static int[] arr;
	static int[][] map;
	static int position;
	static int turn;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		    n = Integer.parseInt(br.readLine());
		    arr = new int[n];

		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < n; i++) {
		        arr[i] = Integer.parseInt(st.nextToken());
		    }

		    map = new int[n][n];
		    for (int i = 0; i < n; i++) {
		        st = new StringTokenizer(br.readLine());
		        for (int j = 0; j < n; j++) {
		            map[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }

		position = Integer.parseInt(br.readLine());
		recursive(n, arr, 0);

		System.out.println(answer);
		bw.flush();
		bw.close();
		br.close();
	}

	// 죽은사람은 888로 설정한다.
	public static void recursive(int turn, int gameList[], int result) {
		// 시민이 한명도 남지 않았을 경우 종료
		// 마피아가 죽었을 경우 종료
		if (check(gameList)) {
//			System.out.println(Arrays.toString(gameList));
			answer = Math.max(answer, result);
			return;
		}

		int[] temp = new int[n];
		temp = gameList.clone();

		if (turn % 2 == 0) {
			for (int i = 0; i < n; i++) {
				if (arr[i] != 888 && i != position) {
					mafia(i, gameList);
					recursive(turn - 1, gameList, result + 1);
					System.arraycopy(temp, 0, gameList, 0, n);
				}
			}
		} else {
			citizen(gameList);
			recursive(turn - 1, gameList, result);
			
			gameList= temp.clone();
		}
	}

	
	// 마피아 실행 함수
	public static void mafia(int idx, int[] gameList) {
		gameList[idx] = 888;
		for (int i = 0; i < n; i++) {
			if (gameList[i] != 888) {
				gameList[i] = map[idx][i];
			}
		}
	}

	// 사람 실행 함수
	public static void citizen(int[] gameList) {
		int minIndex = -1;
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (gameList[i] != 888 && gameList[i] > maxValue) {
				maxValue = gameList[i];
				minIndex = i;
			}
		}
		// 죽일 사람이 없음.
			gameList[minIndex] = 888;
	}

	public static boolean check(int[] checkList) {
		//죽었을 때	은진이 자리
		
		if (checkList[position] == 888) {
			return true;
		}
		int citizenCnt = 0;
		for (int i = 0; i < n; i++) {
			if (i != position && checkList[i] != 888) {
				citizenCnt++;
			}
		}
		//사람이 하나 남았을
		if(citizenCnt ==0) {
			return true;
		}
		return false;
		
	}

}
