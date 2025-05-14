import java.util.*;

class Solution {
    
    static List<Node>[] list;
    
    static int[] distS;
    static int[] distA;
    static int[] distB;
    static int n;
    static int s;
    static int a;
    static int b;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
    
        list = new ArrayList[n+1];
        distS = new int[n+1];
        distA = new int[n+1];
        distB = new int[n+1];
        
        for(int i=1;i<n+1;i++){
            list[i] = new ArrayList();
        }
        
        for(int i=0;i<fares.length ;i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            
            list[start].add(new Node(end , cost));
            list[end].add(new Node(start , cost));
        }
        
        
        Arrays.fill(distS , Integer.MAX_VALUE);
        Arrays.fill(distA , Integer.MAX_VALUE);
        Arrays.fill(distB , Integer.MAX_VALUE);
        
        dikstra(s , this.distS);
        dikstra(a , this.distA);
        dikstra(b , this.distB);
        
        
        for(int i = 1 ; i <n+1;i++){
            
            if(distS[i]==Integer.MAX_VALUE){
                continue;
            }
            if(distA[i]==Integer.MAX_VALUE){
                continue;
            }
            if(distB[i]==Integer.MAX_VALUE){
                continue;
            }
            
            int total = distS[i] + distA[i] + distB[i];
            answer = Math.min(answer , total);
            
        }
        
        
        // n : 지점의 갯수
        // s : 출발 지점
        // a : 도착지점
        // b : 도착지점
        // fares : 지점 사이 마다의 거리
        
        
        return answer;
        
        
    }
    
    public static void dikstra(int start , int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue();
        
        dist[start] = 0;
        pq.offer(new Node( start, 0));
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int idx = node.index;
            int cost = node.cost;
            
            if(cost > dist[idx]){
                continue;
            }
            
            for(Node next : list[idx]){
                int sumCost = cost + next.cost;
                
                if(sumCost < dist[next.index]){
                    dist[next.index] = sumCost;
                    pq.offer(new Node(next.index , sumCost));
                }
                
            }
            
            
        }
        
    }
    
    
    
    
    
    
    static class Node implements Comparable<Node>{
        int index;
        int cost;
        
        public Node(int index ,int cost){
            this.index = index;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost , o.cost);
        }
        
    }
}