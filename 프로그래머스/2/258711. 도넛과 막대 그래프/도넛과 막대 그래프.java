import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int max = 0;
        for (int[] e : edges) max = Math.max(max, Math.max(e[0], e[1]));

        int[] in = new int[max + 1];
        int[] out = new int[max + 1];

        for (int[] e : edges) {
            out[e[0]]++;
            in[e[1]]++;
        }

        int gen = 0;
        for (int i = 1; i <= max; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                gen = i;
                break;
            }
        }

        int bars = 0, eights = 0;
        for (int i = 1; i <= max; i++) {
            if (in[i] == 0 && out[i] == 0) continue;
            if (i != gen && out[i] >= 2) eights++;
            if (out[i] == 0) bars++;
        }

        int donuts = out[gen] - bars - eights;
        return new int[]{gen, donuts, bars, eights};
    }
}
