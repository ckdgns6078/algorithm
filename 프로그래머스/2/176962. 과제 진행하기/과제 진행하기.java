import java.util.*;

class Solution {
    
    static class Task {
        private String name;
        private int start;
        private int playtime;
        
        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        public Task(String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }
    }
    
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        PriorityQueue<Task> pq = new PriorityQueue<>(
            (o1, o2) -> (o1.start - o2.start)
        );
        
        for(int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            
            String[] str = plans[i][1].split(":");
            int h = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int start = (h * 60) + m;
            
            int time = Integer.parseInt(plans[i][2]);
            
            pq.add(new Task(name, start, time));
        }
        
        Stack<Task> remainingTasks = new Stack<>();
        
        while(!pq.isEmpty()) {
            Task currentTask = pq.poll();
            
            String curName = currentTask.name;
            int curStart = currentTask.start;
            int curPlaytime = currentTask.playtime;
        
            int currentTime = curStart;
            
            if(!pq.isEmpty()) {
                Task nextTask = pq.peek();
                
                if(currentTime + curPlaytime < nextTask.start) {
                    answer.add(curName);
                    currentTime += curPlaytime;
                    
                    while(!remainingTasks.isEmpty()) {
                        Task rem = remainingTasks.pop();
                        
                        if(currentTime + rem.playtime <= nextTask.start) {
                            currentTime += rem.playtime;
                            answer.add(rem.name);
                            continue;
                        } 
                        else {
                            int t = rem.playtime - (nextTask.start - currentTime);
                            remainingTasks.push(new Task(rem.name, t));
                            break;
                        }
                    }
                }
                // 지금 과제 끝내면 새로운 과제 시작할 시간인 경우
                else if(curStart + curPlaytime == nextTask.start) {
                    answer.add(curName);
                    continue;
                }
                // 새로운 과제 시작전까지 지금 과제를 못 끝내는 경우
                else {
                    int t = (nextTask.start - currentTime);
                    remainingTasks.push(new Task(curName, curPlaytime - t));
                }
                
            }
            
            // 더 이상 남아있는 새로운 과제가 없는 경우
            else {
                // 남아있는 과제(잠시 멈춘 과제)도 없는 경우
                if(remainingTasks.isEmpty()) {
                    currentTime += curPlaytime;
                    answer.add(curName);
                }
                // 남아있는 과제는 있는 경우
                else {
                    answer.add(curName); // 새로운 과제부터 먼저 해결
                    
                    // 남아있는 과제들을 정해진 순서대로 끝내면 됨
                    while(!remainingTasks.isEmpty()) {
                        Task rem = remainingTasks.pop();
                        answer.add(rem.name);
                    }
                }
            }
        }
        
        return answer;
    }
}