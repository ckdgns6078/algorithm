import java.io.*;
import java.util.*;

public class Main {
    static int k , m , n;
    static int[] code;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();
        code = new int[m];
        
        for(int i=0;i<m;i++){
            code[i] = sc.nextInt();
        }

        arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        if(m>n){
            System.out.println("normal");
            System.exit(0);
        }
        boolean check = false;
        for(int i=0 ; i<=n-m ;i++){
            int temp = 0;
            int cnt=0;
            for(int j=i ; j<i+m;j++){
                if(code[cnt] == arr[j]){
                    temp++;
                }
                cnt++;
            }
            if(temp == m){
                check = true;
                break;
            }
        }

        if(check){
            System.out.println("secret");
        }else{
            System.out.println("normal");
        }

        
    }
}
