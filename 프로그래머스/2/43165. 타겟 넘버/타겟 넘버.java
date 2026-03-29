class Solution {
    public static int answer = 0;
    public int solution(int[] numbers, int target) {
        
        
        run(numbers , target , 0 , 0);
        
        return answer;
    }
    
    public static void run(int[] numbers , int target , int idx , int sum){
        if(idx == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        run(numbers , target , idx+1 , sum + numbers[idx]);
        run(numbers , target , idx+1 , sum + (numbers[idx] *-1));
    }
}

//dfs 를 활용해서 문제를 풀이한다.
// 처음부터 반복을 한다. 시작한 값을 음수, 양수를 만들어서 결과값이 나올 경우 answer를 더하고 아니면 뺀다.