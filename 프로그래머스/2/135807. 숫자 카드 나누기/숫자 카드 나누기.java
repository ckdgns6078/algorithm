import java.util.*;

class Solution {
    
    
    static Set<Integer> setA = new TreeSet();
    static Set<Integer> setB = new TreeSet();
    
    
    public int solution(int[] arrayA, int[] arrayB) {
        //영희가 있거나 철수가 없는 경우
        //철수가 있거나 영희가 없는 경우
        // 두개 중 가장 큰 수
        int answer = 0;
        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        for(int i=0;i< arrayA.length; i++){
            minA = Math.min(minA , arrayA[i]);
            minB = Math.min(minB , arrayB[i]);
        }

        for(int i=1; i<= Math.sqrt(minA) ; i++){
            if(minA % i ==0){
                setA.add(i);
                setA.add(minA / i);
            }
        }
        
        for(int i=1 ; i<= Math.sqrt(minB) ; i++){
            if(minB % i ==0){
                setB.add(i);
                setB.add(minB / i);
            }
        }
        
        List<Integer> listA = new ArrayList(setA);
        List<Integer> listB = new ArrayList(setB);
        
        
        
        for(int i= listA.size()-1 ; i>=0 ; i--){
            int cnt = listA.get(i);                        
            if(checkA(cnt , arrayA , arrayB)){
                answer= Math.max(cnt , answer);
            }
        }
        
        for(int i= listB.size()-1 ; i>=0 ; i--){
            int cnt = listB.get(i);
            if(checkB(cnt , arrayA , arrayB)){
                answer = Math.max(cnt , answer);
            }
        }
        
    
        return answer;
    }
    
    
    public static boolean checkA(int cnt , int[] arrayA , int[] arrayB){
        
        for(int i=0;i<arrayA.length ;i++){
            int temp = arrayA[i];
            if(temp % cnt !=0){
                return false;
            }
        }
        
        for(int i=0;i<arrayB.length ;i++){
            int temp = arrayB[i];
            if(temp % cnt ==0){
                return false;
            }
        }
        
        
        
        return true;
    }
    
    
    public static boolean checkB(int cnt , int[] arrayA , int[] arrayB){
        
        for(int i=0;i<arrayA.length ;i++){
            int temp = arrayA[i];
            if(temp % cnt ==0){
                return false;
            }
        }
        
        for(int i=0;i<arrayB.length ;i++){
            int temp = arrayB[i];
            if(temp % cnt !=0){
                return false;
            }
        }
        
        return true;
    }
    
    
    
}