import java.util.*;

class Solution {
    public int[] solution(String s) {
        
        String[] parts = s.substring(2, s.length()-2).split("\\},\\{");
        Arrays.sort(parts, (a, b) -> a.length() - b.length());
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (String part : parts) {
            String[] nums = part.split(",");
            for (String num : nums) {
                int val = Integer.parseInt(num);
                if (!seen.contains(val)) {
                    seen.add(val);
                    result.add(val);
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}