import java.util.*;

class Solution {
    List<Set<Integer>> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        int colSize = relation[0].length;

        // 1개 ~ colSize개 컬럼 조합
        for (int r = 1; r <= colSize; r++) {
            combine(colSize, r, 0, new LinkedHashSet<>(), relation);
        }

        return candidateKeys.size();
    }

    private void combine(int n, int r, int start, Set<Integer> comb, String[][] relation) {
        if (comb.size() == r) {
            // 최소성 먼저 확인
            for (Set<Integer> key : candidateKeys) {
                if (comb.containsAll(key)) return;
            }

            // 유일성 확인
            if (isUnique(comb, relation)) {
                candidateKeys.add(new HashSet<>(comb));
            }
            return;
        }

        for (int i = start; i < n; i++) {
            comb.add(i);
            combine(n, r, i + 1, comb, relation);
            comb.remove(i);
        }
    }

    private boolean isUnique(Set<Integer> comb, String[][] relation) {
        Set<String> seen = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int col : comb) {
                sb.append(row[col]).append("|");
            }
            seen.add(sb.toString());
        }

        return seen.size() == relation.length;
    }
}
