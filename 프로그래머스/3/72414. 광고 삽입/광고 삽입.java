import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        long[] time = new long[360000 + 1];
        
        for (int i = 0; i < logs.length; i++) {
            String log[] = logs[i].split("-");
            int startTime = (int) timeToLong(log[0]);
            int endTime = (int) timeToLong(log[1]);
            
            time[startTime]++; 
            time[endTime]--;
        }

        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
        }

        int advTime = (int) timeToLong(adv_time);
        int playTime = (int) timeToLong(play_time);

        long sum = 0;
        for (int i = 0; i < advTime; i++) {
            sum += time[i];
        }

        long max = sum;
        int idx = 0;

        for (int i = advTime; i <= playTime; i++) {
            sum += time[i] - time[i - advTime];
            if (sum > max) {
                max = sum;
                idx = i - advTime + 1;
            }
        }
        
        return changeTime(idx);
    }
    
    public long timeToLong(String time){
        String[] split = time.split(":");
        long hour = Long.parseLong(split[0]);
        long min = Long.parseLong(split[1]);
        long sec = Long.parseLong(split[2]);
        return (hour * 3600) + (min * 60) + sec;
    }

    public String changeTime(int time){
        int h = time / 3600;
        String hour = ""+h;
        if(hour.length()<2){
            hour = "0"+hour;
        }else if (hour.equals("0")){
            hour = "00";
        }
        time -= 3600 * h;

        int m = time / 60;
        String min = ""+m;
        if(min.length()<2){
            min = "0"+min;
        }else if(min.equals("0")){
            min = "00";
        }
        
        time -= 60 * m;
        
        int s = time;
        String sec = ""+s;
        if(sec.length()<2){
            sec = "0" + sec;
        }else if(sec.equals("0")){
            sec = "00";
        }
        return hour + ":" + min +":" + sec;
    }
}
