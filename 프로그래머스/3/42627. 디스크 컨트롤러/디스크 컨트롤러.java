import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        int sum = 0;
        
        PriorityQueue<Task> pq = new PriorityQueue();
        Arrays.sort(jobs, Comparator.comparingInt(j -> j[0]));
        int size = jobs.length;
        
        int nowTime =0;
        boolean working = false;
        int workEnd = 0;
        int workStart = 0;
        int wantTime =0;
        int temp =0;
        
        int cnt =0;
        while(cnt<size){
            //작업이 있을 경우 작업이 종료 되었는지 검사한다.
            if(working && workEnd == nowTime){
                working = false;
                sum += workEnd - wantTime;
                cnt++;
            }
            //현재 시간이 요청시간이면 pq에 넣는다.
            //index가 더 크면 더 이상 실행하지 않는다.
            if(temp < size){
                for(int i=temp; i< size;i++){
                    int st = jobs[i][0];
                    if(st <=nowTime){
                        pq.offer(new Task(i , jobs[i][0] , jobs[i][1]));
                        temp = i+1;
                    }else{
                        break;
                    }
                }
            }
            
            //실행중인 작업이 없을 경우 pq에서 빼서 넣는다.
            if(!working && pq.size()>0){
                Task t = pq.poll();
                working = true;
                workEnd = nowTime + t.workTime;
                workStart = nowTime;
                wantTime =t.time;              
            }
            nowTime++;
        }
        
        
        
        return sum/size;
    }
    
    static class Task implements Comparable<Task>{
        int idx;    //q작업 번호
        int time;   //요청 시각
        int workTime;//소요 시간
        
        public Task(int idx , int time , int workTime){
            this.idx = idx;
            this.time = time;
            this.workTime = workTime;
        }
        
        @Override
        public int compareTo(Task o){
            //작업 소요시간이 짧은것 workTime
            if(this.workTime != o.workTime){
                return Integer.compare(this.workTime , o.workTime);
            }
            if(this.time != o.time){
                return Integer.compare(this.time , o.time);
            }
            return Integer.compare(this.idx , o.idx);
        }
    }
}