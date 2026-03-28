import java.util.*;


class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Node> q= new ArrayDeque();
        for(int i=0;i<priorities.length;i++){
            q.offer(new Node(i , priorities[i]));
        }
        
        while(!q.isEmpty()){
            
            Node n = q.poll();
            boolean biggest = true;
            
            for(Node next : q){
                if(next.imp > n.imp){
                    biggest = false;
                    break;
                }
            }
            
            if(biggest){
                answer++;
                if(n.idx == location) {
                    return answer;
                }
            }else{
                q.offer(n);
            }
        }
        
        
        return answer;
    }
    
    class Node{
        int idx;
        int imp;
        
        Node(int idx , int imp){
            this.idx = idx;
            this.imp = imp;
        }
    }
}

// 맨앞에 있는 값을 꺼내는데 그것보다 높은 값이 있을 경우 그 값을 꺼내야한다 .
// 큐를 사용한다. 큐를 사용하는데 