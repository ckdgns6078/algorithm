import java.util.Scanner;

public class Main {
    static String S, T;
    static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.next();
        T = sc.next();
        recursvie(T);
        System.out.println(found ? 1 : 0);
    }

    public static void recursvie(String current) {
        if (current.length() < S.length() || found) return;

        if (current.equals(S)) {
            found = true;
            return;
        }

        if (current.charAt(current.length() - 1) == 'A') {
        	recursvie(current.substring(0, current.length() - 1));
        }

        if (current.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(current).reverse();
            recursvie(sb.substring(0, sb.length() - 1));
        }
    }
}
