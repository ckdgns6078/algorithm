import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            int M = Integer.parseInt(br.readLine()); // 수열의 크기
            int[] arr = new int[M];

            // 수열 입력 받기 (BufferedReader 사용)
            int index = 0;
            while (index < M) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    arr[index++] = Integer.parseInt(st.nextToken());
                }
            }

            // 최소 합 찾기
            sb.append(findMinimumSum(arr, M)).append("\n");
        }

        System.out.print(sb.toString());
    }

    // 최소 구간 합을 찾는 함수
    private static int findMinimumSum(int[] arr, int M) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // 1부터 totalSum의 약수를 오름차순으로 정렬하여 리스트에 저장
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= totalSum; i++) {
            if (totalSum % i == 0) {
                divisors.add(i);
                if (i != totalSum / i) {
                    divisors.add(totalSum / i);
                }
            }
        }
        Collections.sort(divisors); // 오름차순 정렬

        // 작은 값부터 가능한지 확인
        for (int target : divisors) {
            if (canDivide(arr, M, target)) {
                return target;
            }
        }

        return totalSum; // 모든 경우 실패하면 전체 합이 최소 값
    }

    // 특정 targetSum으로 구간을 나눌 수 있는지 확인하는 함수
    private static boolean canDivide(int[] arr, int M, int targetSum) {
        int currentSum = 0;

        for (int num : arr) {
            currentSum += num;
            if (currentSum > targetSum) { // 구간 합이 targetSum을 초과하면 실패
                return false;
            }
            if (currentSum == targetSum) { // targetSum이 되면 새로운 구간 시작
                currentSum = 0;
            }
        }

        return currentSum == 0; // 정확히 나누어진 경우만 true 반환
    }
}
