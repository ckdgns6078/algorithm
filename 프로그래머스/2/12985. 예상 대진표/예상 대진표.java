class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;

        
        while(true){
            
            a = run(a);
            b = run(b);
            if(a == b){
                break;
            }
            
            answer++;
        }
        
        
        return answer;
    }
    
    public static int run(int num){
        
        if(num == 1){
            return 1;
        }
        if( num % 2 == 0){
            return num / 2;
        }
        return (num / 2) + 1;
        
    }
}