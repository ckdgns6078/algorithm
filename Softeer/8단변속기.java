import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr= new int[8];
        for(int i=0;i<arr.length ; i++){
            arr[i] = sc.nextInt();
        }
        int temp = 0;
        if(arr[0]==1){
            temp=1;
            for(int i=1;i<8;i++){
                if(arr[i]-temp==1){
                    temp++;
                }else{
                    System.out.println("mixed");
                    break;
                }
                if(temp==8){
                    System.out.println("ascending");
                }
            }
        }else if( arr[0]==8){
            temp = 8;
            for(int i=1;i<8;i++){
                if(temp-arr[i]==1){
                    temp--;
                }else{
                    System.out.println("mixed");
                    break;
                }
                if(temp==1){
                    System.out.println("descending");
                }
            }
        }else{
            System.out.println("mixed");
        }
        
        



        
    }
}
