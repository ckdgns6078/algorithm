import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int cnt = sc.nextInt(); // 조카의 수
        int snackSize = sc.nextInt(); // 과자의 수
        int[] snacks = new int[snackSize];
        
        // 과자 입력
        for (int i = 0; i < snackSize; i++) {
            snacks[i] = sc.nextInt();
        }
        
        // 과자 정렬
        Arrays.sort(snacks);

        // 이분 탐색 초기화
        int left = 1; // 최소 과자 길이는 1
        int right = snacks[snackSize - 1]; // 최대 과자 길이
        int answer = 0;

        while (left <= right) {
            int middle = (left + right) / 2;
            int count = 0;

            // 현재 중간 길이로 몇 명에게 나눠줄 수 있는지 계산
            for (int i = 0; i < snackSize; i++) {
                count += snacks[i] / middle;
            }

            if (count >= cnt) { // 모든 조카에게 나눠줄 수 있는 경우
                answer = middle; // 가능한 최대 길이를 갱신
                left = middle + 1; // 더 긴 길이를 탐색
            } else { // 나눠줄 수 없는 경우
                right = middle - 1; // 더 짧은 길이를 탐색
            }
        }

        // 결과 출력
        System.out.println(answer);
    }
}
