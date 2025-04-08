import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[] titles;
    static long[] limits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        titles = new String[n];
        limits = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            titles[i] = st.nextToken();
            limits[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            long power = Long.parseLong(br.readLine());
            int idx = binarySearch(power);
            sb.append(titles[idx]).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int binarySearch(long power) {
        int left = 0, right = n - 1;
        int result = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (power <= limits[mid]) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
