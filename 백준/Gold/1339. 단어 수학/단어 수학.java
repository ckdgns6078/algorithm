import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final int MAX_DIGIT = 9;
    private static List<Character> uniqueChars;
    private static Map<Character, Integer> charToDigit;
    private static List<String> words;
    private static int maxResult = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 단어의 개수
        words = new ArrayList<>(n);
        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String word = sc.next();
            words.add(word);
            for (char c : word.toCharArray()) {
                charSet.add(c);
            }
        }

        uniqueChars = new ArrayList<>(charSet);
        Collections.sort(uniqueChars, (a, b) -> getWeight(b) - getWeight(a));

        charToDigit = new HashMap<>();
        permute(0, new int[uniqueChars.size()], new boolean[uniqueChars.size()]);

        System.out.println(maxResult);
    }

    private static void permute(int index, int[] digits, boolean[] used) {
        if (index == digits.length) {
            calculateResult(digits);
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (!used[i]) {
                used[i] = true;
                digits[index] = i;
                permute(index + 1, digits, used);
                used[i] = false;
            }
        }
    }

    private static void calculateResult(int[] digits) {
        // 문자와 숫자 매핑 설정
        for (int i = 0; i < uniqueChars.size(); i++) {
            charToDigit.put(uniqueChars.get(i), MAX_DIGIT - digits[i]);
        }

        int currentSum = 0;
        for (String word : words) {
            int number = 0;
            for (char c : word.toCharArray()) {
                number = number * 10 + charToDigit.get(c);
            }
            currentSum += number;
        }

        maxResult = Math.max(maxResult, currentSum);
    }

    private static int getWeight(char c) {
        int weight = 0;
        for (String word : words) {
            if (word.indexOf(c) != -1) {
                weight += Math.pow(10, word.length() - word.indexOf(c) - 1);
            }
        }
        return weight;
    }
}