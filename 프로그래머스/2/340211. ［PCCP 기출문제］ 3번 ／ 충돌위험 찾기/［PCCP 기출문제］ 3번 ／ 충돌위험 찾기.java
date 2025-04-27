import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int n = points.length;     // 포인트 개수
        int x = routes.length;     // 로봇 수
        int maxTime = 0;           // 총 시간 측정
        
        // 로봇들의 위치를 시간별로 저장 (List<Map<String, Integer>>)
        List<Map<String, Integer>> timeMap = new ArrayList<>();
        
        // 로봇별 이동 경로 계산
        List<List<int[]>> robotPaths = new ArrayList<>();
        
        for (int i = 0; i < x; i++) {
            List<int[]> path = new ArrayList<>();
            int[] startPoint = points[routes[i][0] - 1];
            path.add(new int[]{startPoint[0], startPoint[1]});
            
            for (int j = 1; j < routes[i].length; j++) {
                int[] prev = points[routes[i][j - 1] - 1];
                int[] next = points[routes[i][j] - 1];
                
                int r = prev[0];
                int c = prev[1];
                int targetR = next[0];
                int targetC = next[1];
                
                // r 먼저 이동
                while (r != targetR) {
                    r += (r < targetR) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
                // 그 다음 c 이동
                while (c != targetC) {
                    c += (c < targetC) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
            }
            robotPaths.add(path);
            maxTime = Math.max(maxTime, path.size());  // 최대 시간 업데이트
        }
        
        // 시간별 위치 기록
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> curTime = new HashMap<>();
            for (int i = 0; i < x; i++) {
                List<int[]> path = robotPaths.get(i);
                if (t >= path.size()) continue; // 운송을 완료한 로봇은 무시
                
                int[] pos = path.get(t);
                String key = pos[0] + "," + pos[1];
                curTime.put(key, curTime.getOrDefault(key, 0) + 1);
            }
            timeMap.add(curTime);
        }
        
        // 위험 상황 계산
        int danger = 0;
        for (Map<String, Integer> curTime : timeMap) {
            for (int cnt : curTime.values()) {
                if (cnt >= 2) {
                    danger++;
                }
            }
        }
        
        return danger;
    }
}
