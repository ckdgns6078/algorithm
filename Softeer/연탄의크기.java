import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int answer = 0;
        
        for(int k = 2; k <= 100; k++) { // 연탄 반지름을 2부터 100까지 시도
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] % k == 0) { // 난로 반지름이 k의 배수라면 count 증가
                    count++;
                }
            }
            answer = Math.max(answer, count); // 최대 집 개수 업데이트
        }
        
        System.out.println(answer);
    }
}
