import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long k = sc.nextLong();
        int p = sc.nextInt();
        int n = sc.nextInt();

        for(int i=0;i<n;i++){
            k = (k*p)% 1000000007;
        }
        System.out.println(k);
    }
}
