import java.util.*;

public class Main {
    static class Soldier {
        int p, q, i;
        Soldier(int p, int q, int i) {
            this.p = p;
            this.q = q;
            this.i = i;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Soldier[] soldiers = new Soldier[N];
        int[] pArr = new int[N];
        int[] qArr = new int[N];
        int[] iArr = new int[N];

        for (int j = 0; j < N; j++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            int i = sc.nextInt();
            soldiers[j] = new Soldier(p, q, i);
            pArr[j] = p;
            qArr[j] = q;
            iArr[j] = i;
        }

        int answer = Integer.MAX_VALUE;

        for (int power : pArr) {
            for (int quick : qArr) {
                for (int brain : iArr) {
                    int cnt = 0;
                    for (Soldier s : soldiers) {
                        if (s.p <= power && s.q <= quick && s.i <= brain) {
                            cnt++;
                        }
                    }
                    if (cnt >= K) {
                        answer = Math.min(answer, power + quick + brain);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
