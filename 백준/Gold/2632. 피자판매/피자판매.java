import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = scan.nextInt();
        int a = scan.nextInt();
        int b = scan.nextInt();

        int[] a_pizza = new int[a];
        int max_a = 0;
        for (int i = 0; i < a; i++) {
            a_pizza[i] = scan.nextInt();
            max_a += a_pizza[i];
        }

        int[] b_pizza = new int[b];
        int max_b = 0;
        for (int i = 0; i < b; i++) {
            b_pizza[i] = scan.nextInt();
            max_b += b_pizza[i];
        }

        int[] a_count = new int[Math.max(max_a, size) + 1];
        a_count[0] = 1;
        a_count[max_a] = 1;
        count(a_pizza, a_count, size);

        int[] b_count = new int[Math.max(max_b, size) + 1];
        b_count[0] = 1;
        b_count[max_b] = 1;
        count(b_pizza, b_count, size);

        int result = 0;
        for (int i = 0; i <= size; i++) {
            result += a_count[i] * b_count[size - i];
        }

        System.out.println(result);
    }

    public static void count(int[] pizza, int[] count, int size) {
        for (int i = 0; i < pizza.length; i++) {
            int sum = 0;
            for (int j = 1; j < pizza.length; j++) {
                int sum_j = pizza[(i + j) % pizza.length];
                if (sum + sum_j > size) break;
                sum += sum_j;
                count[sum]++;
            }
        }
    }
}
