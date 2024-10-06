import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] result = new int[n + 1];

        // 배열 입력 받기
        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(input[i - 1]);
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            // 스택의 가장 높은 탑을 확인
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }

            // 스택이 비어 있지 않다면, 가장 높은 탑의 인덱스를 결과에 저장
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            } else {
                result[i] = 0; // 높은 탑이 없을 경우 0 저장
            }

            // 현재 탑의 인덱스를 스택에 추가
            stack.push(i);
        }

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            bw.write(result[i] + " ");
        }

        bw.flush(); // 출력 버퍼를 비웁니다.
        bw.close(); // BufferedWriter를 닫습니다.
        br.close(); // BufferedReader를 닫습니다.
    }
}