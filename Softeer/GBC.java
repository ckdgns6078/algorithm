import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] nlist;
    static int[][] mlist;
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        nlist = new int[n][2];
        mlist = new int[m][2];

        for(int i=0;i<n;i++){
            nlist[i][0] = sc.nextInt();
            nlist[i][1] = sc.nextInt();
        }
        for(int i=0;i<m;i++){
            mlist[i][0] = sc.nextInt();
            mlist[i][1] = sc.nextInt();
        }

        int result = 0;
         int nidx =0;
         int midx = 0;
         while(nidx < n &&midx < m){
            if(nlist[nidx][0] >= mlist[midx][0]){
                nlist[nidx][0] -= mlist[midx][0];
                mlist[midx][0]=0;
            }else{
                mlist[midx][0] -= nlist[nidx][0];
                nlist[nidx][0] = 0;
            }
            if(nlist[nidx][1]<mlist[midx][1]){
                result =  Math.max(result , mlist[midx][1] - nlist[nidx][1]);
            }

            if(nlist[nidx][0]==0){
                nidx++;
            }
            if(mlist[midx][0]==0){
                midx++;
            }
         }

         
        System.out.println(result);
    }
}
