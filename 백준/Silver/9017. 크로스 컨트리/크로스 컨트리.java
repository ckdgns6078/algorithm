import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            int N = sc.nextInt();
            int[] runners = new int[N];
            Map<Integer, Integer> teamCount = new HashMap<>();

            for (int i = 0; i < N; i++) {
                runners[i] = sc.nextInt();
                teamCount.put(runners[i], teamCount.getOrDefault(runners[i], 0) + 1);
            }

            // 등수별로 팀의 주자들을 저장
            Map<Integer, List<Integer>> teamRanks = new HashMap<>();
            int rank = 1;
            for (int i = 0; i < N; i++) {
                int team = runners[i];
                if (teamCount.get(team) < 6) continue;

                teamRanks.putIfAbsent(team, new ArrayList<>());
                teamRanks.get(team).add(rank++);
            }

            int winner = 0;
            int minScore = Integer.MAX_VALUE;
            int tieBreaker = Integer.MAX_VALUE;

            for (int team : teamRanks.keySet()) {
                List<Integer> ranksList = teamRanks.get(team);
                if (ranksList.size() < 5) continue;

                int sumTop4 = ranksList.get(0) + ranksList.get(1) + ranksList.get(2) + ranksList.get(3);
                int fifth = ranksList.get(4);

                if (sumTop4 < minScore) {
                    minScore = sumTop4;
                    tieBreaker = fifth;
                    winner = team;
                } else if (sumTop4 == minScore) {
                    if (fifth < tieBreaker) {
                        tieBreaker = fifth;
                        winner = team;
                    }
                }
            }

            sb.append(winner).append("\n");
        }

        System.out.println(sb.toString());
    }
}
