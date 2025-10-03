import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        
        //fee 0 : 기본시간
        //fee 1 : 기본 요금
        // fee 2 : 단위 시간
        // fee 3 : 단위 요금
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        //LocalTime t1 = LocalTime.parse(String , formatter);
        //계산 : long min = Duration.between(t1,t2).toMinutes();
        
        
        Set<String> set = new HashSet();
        //정답을 저장하는 TreeMap
        //일반 정보를 저장하는 데이터
        Map<String, List<String[]>> map = new HashMap();
        //시간 정보를 저장하는 데이터
        Map<String, Long> timeMap = new HashMap();
        
        TreeMap<String, Integer> treeMap = new TreeMap();
        
        for(String record : records){
            String[] data = record.split(" ");
            String time = data[0];
            String number = data[1];
            String kind = data[2];
            
            set.add(number);
            
            if(!map.containsKey(number)){
                map.put(number , new ArrayList());
            }
            
            if(kind.equals("OUT")){
                int idx = map.get(number).size()-1;
                String inTime = map.get(number).get(idx)[0];
                
                //시간 계산
                LocalTime in = LocalTime.parse(inTime , formatter);
                LocalTime out = LocalTime.parse(time , formatter);
                
                long min = Duration.between(in, out).toMinutes();
                if(!timeMap.containsKey(number)){
                    timeMap.put(number, 0L);
                }
                timeMap.put(number , timeMap.get(number) + min);
            }
            
            map.get(number).add(new String[] {time , kind});
            
        }
        
        for(String num : set){
            
            int lastIdx = map.get(num).size()-1;
            String kind = map.get(num).get(lastIdx)[1];
            if(kind.equals("IN")){
                String inTime = map.get(num).get(lastIdx)[0];
                String outTime = "23:59";
                
                LocalTime in = LocalTime.parse(inTime , formatter);
                LocalTime out = LocalTime.parse(outTime , formatter);
                
                long min = Duration.between(in, out).toMinutes();
                
                if(!timeMap.containsKey(num)){
                    timeMap.put(num , 0L);
                }
                timeMap.put(num , timeMap.get(num) + min);
            }
            
            //돈 계산
            int pay = calculator(fees , timeMap.get(num));
            treeMap.put(num , pay);
        }
        
        int[] answer = new int[set.size()];   
        int idx =0;
        for(String key : treeMap.keySet()){
            answer[idx] = treeMap.get(key);
            idx++;
        }
        
        return answer;
    }
    
    public int calculator(int[] fees, long time){
        
        if(time <= fees[0]){
            return fees[1];
        }
        
        long extraTime = time - fees[0];
        long unit = (extraTime + fees[2] -1) / fees[2];
        return fees[1] + (int)(unit * fees[3]);
    }
    
}