import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[3][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i=0;i<3;i++){
            for(int j=0;j<3 ;j++){
                map[i][j] = sc.nextInt();
            }
        }

        int answer = Math.min(findWith() , findHeight());
        System.out.println(answer);
    }
    public static int findWith(){
        int cnt =Integer.MAX_VALUE;
        
        for(int i=1;i<4;i++){
            for(int j=0;j<3;j++){
                cnt = Math.min(cnt , Math.abs(i - map[j][0]) + Math.abs(i-map[j][1]) + Math.abs(i-map[j][2]));
            }
        }
        
        return cnt;
    }

    public static int findHeight(){
        int cnt = Integer.MAX_VALUE;

        for(int i=1;i<4;i++){
            for(int j=0;j<3;j++){
                cnt = Math.min(cnt ,Math.abs(i - map[0][j]) + Math.abs(i - map[1][j]) + Math.abs(i - map[2][j]));
                
            }
        }
        return cnt;
    }
}
