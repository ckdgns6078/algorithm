import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] dron;
    static int[][] eat;
    static ArrayList<Integer>[][] treeList;
    static ArrayList<Integer>[][] dieList;

    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        dron = new int[N + 1][N + 1];
        eat = new int[N + 1][N + 1];
        treeList = new ArrayList[N + 1][N + 1];
        dieList = new ArrayList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dron[i][j] = sc.nextInt();
                eat[i][j] = 5;
                treeList[i][j] = new ArrayList<>();
                dieList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int age = sc.nextInt();
            treeList[r][c].add(age);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(countAlive());
    }

    public static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (treeList[i][j].isEmpty()) continue;

                Collections.sort(treeList[i][j]);
                int eats = eat[i][j];
                ArrayList<Integer> survived = new ArrayList<>();

                for (int age : treeList[i][j]) {
                    if (eats >= age) {
                        eats -= age;
                        survived.add(age + 1);
                    } else {
                        dieList[i][j].add(age);
                    }
                }

                treeList[i][j] = survived;
                eat[i][j] = eats;
            }
        }
    }

    public static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int age : dieList[i][j]) {
                    eat[i][j] += age / 2;
                }
                dieList[i][j].clear();
            }
        }
    }

    public static void autumn() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int age : treeList[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];

                            if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
                                treeList[nr][nc].add(0, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                eat[i][j] += dron[i][j];
            }
        }
    }

    public static int countAlive() {
        int alive = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                alive += treeList[i][j].size();
            }
        }
        return alive;
    }
}