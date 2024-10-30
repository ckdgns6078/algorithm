import java.io.*;
import java.util.*;

public class Main {
    static int size;
    static int n;
    static int answer =0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        n = sc.nextInt();

        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] a , int[]b){
                return Integer.compare(b[1] , a[1]);
            }
        });

        for(int i=0;i<n;i++){
            pq.offer(new int[]{sc.nextInt() , sc.nextInt()});
        }

        while(!pq.isEmpty()){
            int[] value = pq.poll();
            if(size < value[0]){
                answer += size * value[1];
                size -= size;
            }else{
                answer += value[0] * value[1];
                size -= value[0];
            }
            if(size == 0){
                break;
            }    
        }
        

        System.out.println(answer);
                                                    
    }
}
