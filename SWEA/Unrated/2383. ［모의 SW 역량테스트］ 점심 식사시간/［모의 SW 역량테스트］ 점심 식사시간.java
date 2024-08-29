import java.util.*;

public class Solution {
	static int[][] steps; // 계단의 위치 저장
	static ArrayList<int[]> peopleList; // 사람들의 r,c,거리
	static int peopleCnt;
	static int[] subsetArr; // 부분집합을 구하기 위해 사용할 변수
	static ArrayList<boolean[]> subsetList;
	static int[][] step;
	static int result;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			peopleList = new ArrayList();
			subsetList = new ArrayList();
			int n = sc.nextInt();
			steps = new int[2][3];
			step = new int[2][3];
			peopleCnt = 0;
			result= Integer.MAX_VALUE;
			boolean check = false;
			// 입력받아 저장하기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int input = sc.nextInt();
					if (input == 1) {
						peopleList.add(new int[] { i, j, 0, 0 });
						peopleCnt++;
					} else if (input != 1 && input != 0) {
						if (!check) {
							steps[0][0] = i;
							steps[0][1] = j;
							steps[0][2] = input;
							check = true;
						} else {
							steps[1][0] = i;
							steps[1][1] = j;
							steps[1][2] = input;
						}
					}

				}
			}

			subsetArr = new int[peopleCnt];
			for (int i = 0; i < peopleCnt; i++) {
				subsetArr[i] = i;
			}

			// 구현부

			subset(0, new boolean[peopleCnt]);

			// 사이즈 만큼 반복한다.
			for (int i = 0; i < subsetList.size(); i++) {

				// 사이즈가 정해졌다.
				findDistance(subsetList.get(i));
				int waitA = 0; // A에서 기다리는 사람
				int waitB = 0; // B에서 기다리는 사람
				int movePeople = peopleCnt; // 이동해야되는 사람
				int time = 0;
				int outPeople = peopleCnt;
			
//				for( int a = 0 ; a < peopleList.size(); a++) {
//					System.out.println(Arrays.toString(peopleList.get(a)));
//				}
//				
				
				while (outPeople > 0) {

					time++;
					int peopleInCnt = 0;
					if (waitA > 0) {
						peopleInCnt = intoStep(0 , waitA);
						waitA -= peopleInCnt;
					}
					// b에서 기다리는 사람이 있을 경우
					if (waitB > 0) {
						peopleInCnt = intoStep(1 , waitB);
						waitB -= peopleInCnt;
					}

					int a3 = moveStep(0);
					int bb = moveStep(1);
//					System.out.println("현재시간 :" + time + "1번 출구 나간사람 :"+a3 + "2번 출구 나간사람 :" + bb);
					
					outPeople -= a3+bb;

					
					if (movePeople > 0) {
						for (int k = 0; k < peopleList.size(); k++) {
							if (peopleList.get(k)[2] == time) {
								if (peopleList.get(k)[3] == 0) {
									waitA++;
									movePeople--;
								} else {
									waitB++;
									movePeople--;
								}
							}
						}
					}

				}
				
				if(time !=0) {
					result = Math.min(result, time+1);					
				}
			}
			

			System.out.println("#" + tc + " " + result);

		}

	}

	private static int intoStep(int cnt , int people) {
		int inPeople = 0;

		for (int i = 0; i < 3; i++) {
			if (step[cnt][i] == 0 && people>0) {
				step[cnt][i] = 1;
				people--;
				inPeople++;
			}
		}

		return inPeople;
	}

	private static int moveStep(int cnt) {
		int out = 0;

		for (int i = 0; i < 3; i++) {
			if (step[cnt][i] > 0) {
				step[cnt][i]++;
				if (step[cnt][i] > steps[cnt][2]) {
					step[cnt][i] = 0;
					out++;
				}
			}
		}
		return out;

	}

	// 저장한 배열들의 거리를 저장하는 함수
	private static void findDistance(boolean[] v) {
		// true 이면 0번 false 이면 1번
		// 이동 시간(분) = | PR - SR | + | PC - SC |
		// r-도착r + c - 도착 c
		for (int i = 0; i < v.length; i++) {
			int distance = 0;
			if (v[i]) {
				distance = Math.abs(peopleList.get(i)[0] - steps[0][0]) + Math.abs(peopleList.get(i)[1] - steps[0][1]);
				peopleList.get(i)[2] = distance;
				peopleList.get(i)[3] = 0;
			} else {
				distance = Math.abs(peopleList.get(i)[0] - steps[1][0]) + Math.abs(peopleList.get(i)[1] - steps[1][1]);
				peopleList.get(i)[2] = distance;
				peopleList.get(i)[3] = 1;

			}
		}

		// false 이면 1번

	}

	private static void subset(int idx, boolean[] v) {
		if (idx == subsetArr.length) {
			subsetList.add(v.clone());
			return;
		}
		v[idx] = true;
		subset(idx + 1, v);
		v[idx] = false;
		subset(idx + 1, v);

	}

}
