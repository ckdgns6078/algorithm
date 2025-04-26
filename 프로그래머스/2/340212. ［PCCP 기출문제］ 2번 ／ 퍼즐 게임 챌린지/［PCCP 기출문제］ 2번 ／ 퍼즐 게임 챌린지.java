class Solution {
    static int[] diffs;
    static int[] times;
    static long limit;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        answer = binarySearch();
        
        return answer;
    }
    //풀이 : 숙련도가 초기값이 몇인지는 알 수 없다. 하지만 그 값중에서 가장 작은 값을 찾아야함으로
    //최대 시간은 limit으로 설정하면 된다.
    
    
    public static int binarySearch(){
        long left = 1;
        long right = limit;
        long answer = limit;
        while(left<=right){
            long middle = (left + right) / 2;
            
            long check = checkCnt(middle);
            
            if(check <=limit){
                answer= Math.min(answer,  middle);
                right = middle-1;
                
            }else{
                left = middle+1;
            }
        }
        
        return (int)answer;
    }
    
    public static long checkCnt(long level){
        long time = 0;
        
        for(int i=0;i<diffs.length;i++){
            int diff = diffs[i];
            
            if(i ==0){
                time += times[i];
                continue;
            }
            
            if(level>=diff){
                time+=times[i];
            }else{
                long fail = diff-level;
                time+= (times[i] + times[i-1]) * fail + times[i];
            }
            
        }
        return time;
    }
}