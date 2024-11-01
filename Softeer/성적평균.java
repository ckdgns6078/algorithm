import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static int[] arr;
    static int[][] order;
    static double[] merge;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n+1];
        merge = new double[n+1];
        for(int i=1;i<n+1;i++){
            arr[i] = sc.nextInt();
            merge[i] = merge[i-1] + arr[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++){
            int left = sc.nextInt();
            int right = sc.nextInt();
            int cnt = right - left + 1;
            double sum = (merge[right] - merge[left-1]) / cnt;
            sb.append(String.format("%.2f" , sum));
            sb.append("\n");
        }

        System.out.println(sb);
        
        
    }
}
