import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> routes = new HashMap<>();
    List<String> result = new ArrayList<>();

    public String[] solution(String[][] tickets) {
    
        for (String[] ticket : tickets) {
            routes.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

       
        dfs("ICN");
        Collections.reverse(result);
        return result.toArray(new String[0]);
    }

    private void dfs(String airport) {
        PriorityQueue<String> arrivals = routes.get(airport);
        
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        result.add(airport);
    }
}