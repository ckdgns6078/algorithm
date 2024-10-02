import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        boolean[] visited = new boolean[100001];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

		int start = 0;
		int end = 0;
		long answer = 0;

		// answer 계산은 어떻게 해야되는가?
		while (start < n) {
			// end값이 없을 경우
			if (end ==n) {
				answer += (end - start);
				visited[arr[start]] = false;
				start++;
			} else if (!visited[arr[end]]) {
				visited[arr[end]] = true;
				end++;
			} else {
				answer += (end - start);
				visited[arr[start]] = false;
				start++;
			}
			
			
		}
		
		System.out.println(answer);

	}

}
