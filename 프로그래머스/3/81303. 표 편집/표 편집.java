import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        int[] before = new int[n];
        int[] next = new int[n];
        
        //시작 위치 : K
        for(int i=0;i<n;i++){
            before[i] = i-1;
            next[i] = i+1;
        }
        before[0] = -1;
        next[n-1] = -1;
        
        Stack<int[]> stack = new Stack(); // before , next , idx 순
        
        for(String s :  cmd){
            String[] commend = s.split(" ");
            String com = commend[0];
            
            if(com.equals("U")){
                int cnt = Integer.parseInt(commend[1]);
                k = up(before , cnt , k);
            }else if(com.equals("D")){
                int cnt = Integer.parseInt(commend[1]);
                k = down(next , cnt , k);
            }else if(com.equals("C")){
                k = delete(before, next, stack, k);
            }else{  // Z
                rollBack(before, next, stack);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(before[i] ==-1 && next[i] == -1){
                sb.append("X");
            }else{
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    public int up(int[] before, int cnt , int k){
        for(int i=0;i<cnt;i++){
            k = before[k];
        }
        
        return k;
    }
    
    public int down(int[] next, int cnt, int k){
        for(int i=0;i<cnt;i++){
            k = next[k];
        }
        return k;
    }
    
    public int delete(int[] before, int[] next, Stack<int[]> stack, int k){
        
        
        int beforeIdx = before[k];
        int afterIdx = next[k];
        
        stack.push(new int[] { beforeIdx, afterIdx , k});
        
        if(beforeIdx != -1){
            next[beforeIdx] = afterIdx;
        }
        
        if(afterIdx != -1){
            before[afterIdx] = beforeIdx;
        }
        
        before[k] = -1;
        next[k] = -1;
        
        return (afterIdx != -1) ? afterIdx : beforeIdx;
    }
    
    public void rollBack(int[] before , int[] next, Stack<int[]> stack){
        int[] data = stack.pop();
        
        int beforeIdx = data[0];
        int afterIdx = data[1];
        int idx = data[2];
        
        //자기 자신을 복구한다.
        before[idx] = beforeIdx;
        next[idx] = afterIdx;
        
        //기존에 있는 데이터들을 연결한다.
        if (beforeIdx != -1) {
            next[beforeIdx] = idx;
        }
        if (afterIdx != -1) {
            before[afterIdx] = idx;
        }
    }
}