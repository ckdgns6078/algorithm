import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 현재 물병 수
        int K = sc.nextInt(); // 옮길 수 있는 병 수 제한
        int answer = 0;

        while (Integer.bitCount(N) > K) {
            N++;         // 병 하나 더 삼
            answer++;    // 추가 병 개수 증가
        }

        System.out.println(answer);
    }
}
