import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] attack;
    static int enemy =0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map= new int[n+1][m+1];
        attack = new int[2][2];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                map[i][j] = sc.nextInt();
                if(map[i][j]==1){
                    enemy++;
                }
            }
        }
        for(int i=0;i<2;i++){
            attack[i][0]=sc.nextInt();
            attack[i][1]=sc.nextInt();
        }


        for(int i=0;i<2;i++){
            int startIdx = attack[i][0];
            int endIdx = attack[i][1];
            run(startIdx , endIdx);
        }        
        System.out.println(enemy);
        
    }
    private static void run(int start , int end){
        int check = end;
        int[] temp = new int[end+1];
        Arrays.fill(temp,1);
        for(int i=1;i<m+1;i++){
            for(int j=start ; j<=end ; j++){
                if(map[j][i]==1 && temp[j]==1){
                    map[j][i]=0;
                    check--;
                    enemy--;
                    temp[j]=0;
                }
                if(check==0){
                    return;
                }
            }
        }
    }
}
