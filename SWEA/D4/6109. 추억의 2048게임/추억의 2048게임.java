import java.util.*;

public class Solution {
	static int n;
	static int[][] map;
	static String direction;
	// 위 아래 왼 오
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			direction = sc.next();
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 시작 값 , 인덱스 번호

			// 위쪽 [0][i]
			// 아래 [n-1][i]
			// 왼쪽 [i][0]
			// 오른쪽 [i][n-1]

			switch (direction) {
			case "up":
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < n; y++) {
						int start = map[y][x];
						int ny = y + dy[0];
						if (start != 0) {
							while (ny < n) {
								if (map[ny][x] != 0) {
									if (map[ny][x] == start) {
										map[y][x] = start * 2;
										map[ny][x] = 0;
									}
									break;
								}
								ny += dy[0];
							}

							int cnt = 0;
							for (int i = y - 1; i >= 0; i--) {
								if (map[i][x] == 0) {

									cnt++;
								} else {
									break;
								}
							}
							if (cnt > 0) {
								map[y - cnt][x] = map[y][x];
								map[y][x] = 0;
							}
						}
					}
				}
				break;
			case "down":

				for (int x = 0; x < n; x++) {
					for (int y = n - 1; y >= 0; y--) {
						int start = map[y][x];
						int ny = y + dy[1];
						if (start != 0) {
							while (ny >= 0) {
								if (map[ny][x] != 0) {
									if (map[ny][x] == start) {
										map[y][x] = start * 2;
										map[ny][x] = 0;
									}
									break;
								}
								ny += dy[1];
							}
							int cnt = 0;
							for (int i = y + 1; i < n; i++) {
								if (map[i][x] == 0) {
									cnt++;
								} else {
									break;
								}
							}
							if (cnt > 0) {
								map[y + cnt][x] = map[y][x];
								map[y][x] = 0;
							}
						}
					}
				}
				break;
				
			case "left":
				for( int y = 0 ; y < n ; y++) {
					for( int x = 0 ; x < n ; x++) {
						int start = map[y][x];
						int nx = x + 1;
						if( start!=0) {
							while(nx<n) {
								if(map[y][nx]!=0) {
									if(map[y][nx]==start) {
										map[y][x]= start*2;
										map[y][nx] = 0;
									}
									break;
								}
								nx += 1;
							}
							int cnt =0;
							for (int i = x - 1; i >= 0; i--) {
								if(map[y][i]==0) {
									cnt++;
								}else {
									break;
								}
							}
							if(cnt>0) {
								map[y][x-cnt] = map[y][x];
								map[y][x]=0;
							}	
						}		
					}
				}
				break;
				
			
			case "right":
				for( int y = 0 ; y < n ; y++) {
					for( int x = n-1 ; x >=0 ; x--) {
						int start = map[y][x];
						int nx = x -1;
						if( start!=0) {
							while(nx>=0) {
								if(map[y][nx]!=0) {
									if(map[y][nx]==start) {
										map[y][x]= start*2;
										map[y][nx] = 0;
									}
									break;
								}
								nx += -1;
							}
							int cnt =0;
							for (int i = x + 1; i < n ; i++) {
								if(map[y][i]==0) {
									cnt++;
								}else {
									break;
								}
							}
							if(cnt>0) {
								map[y][x+cnt] = map[y][x];
								map[y][x]=0;
							}	
						}		
					}
				}
				break;
			}

			System.out.println("#" + tc +" ");
			for(int i = 0 ; i < n ; i++) {
				for( int j = 0 ; j < n ; j++) {
					System.out.print(map[i][j]+ " ");
				}
				System.out.println();
			}

		}

	}

}
