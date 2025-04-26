class Solution {
    static int[] players;
    static int m;
    static int k;
    static int[] server;
    public int solution(int[] players, int m, int k) {
        this.server = new int[players.length];
        
        this.players = players;
        this.m = m;
        this.k = k;
       
        int answer =0;
        
        
        for(int i=0;i<players.length;i++){
            int player = players[i];
            
            int validSize = (server[i] * m)+m;
            if(player >= validSize){

                int cnt = player - validSize;
                int temp = 1 + (cnt / m);
                createServer(i , temp);
                answer+= temp;
            }
        }
        return answer;
    }
    
    
    //서버를 증설하는 함수
    public static void createServer(int idx , int temp){
        for(int i=idx ; i< idx+k ; i++){
            if(i >= server.length){
                return;
            }
            server[i]+=temp;
        }
    }
 
        
}