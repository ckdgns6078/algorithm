import java.util.*;

class Solution {
    
    static char[][] map;
    static int m;
    static int n;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        map = new char[m][n];
        this.m = m;
        this.n = n;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        
        boolean temp = true;
        while(temp){
            Set<String> set = new HashSet<>();
            
            // 블록 체크
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    blockCheck(i, j, set);
                }
            }
            
            if(set.isEmpty()){
                temp = false;
                continue;
            }
            
            // 제거된 블록 수 더하기
            answer += set.size();
            
            // 삭제
            deleteMap(set);
            
            // 빈칸 채우기
            for(int i=0;i<n;i++){
                moveMap(i);
            }
        }
        
        return answer;
    }
    
    public void moveMap(int c){
        for(int i = m-2; i >= 0; i--){ // 마지막 행 제외
            if(map[i][c] == '0') continue;
        
            int next = i;
            while(next+1 < m && map[next+1][c] == '0'){
                next++;
            }
        
            if(next != i){
                map[next][c] = map[i][c];
                map[i][c] = '0';
            }
        }
    }

    public void blockCheck(int r , int c , Set<String> set){
        char type = map[r][c];
        if(type == '0') return; // 빈 칸은 스킵
        
        if(r+1 >= m || c+1 >= n) return;
        
        char b1 = map[r][c+1];
        char b2 = map[r+1][c];
        char b3 = map[r+1][c+1];
        
        if(type == b1 && type == b2 && type == b3){
            set.add(r + "," + c);
            set.add((r+1) + "," + c);
            set.add(r + "," + (c+1));
            set.add((r+1) + "," + (c+1));
        }
    }
    
    public void deleteMap(Set<String> set){
        for(String s : set){
            String[] arr = s.split(",");
            int r = Integer.parseInt(arr[0]);
            int c = Integer.parseInt(arr[1]);
            map[r][c] = '0';
        }
    }
}
