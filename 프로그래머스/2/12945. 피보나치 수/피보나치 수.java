class Solution {
    static final int MOD = 1234567;

    public int solution(int n) {
        int a = 0, b = 1;      // F(0), F(1)
        for (int i = 2; i <= n; i++) {
            int c = (a + b) % MOD;
            a = b;
            b = c;
        }
        return n == 0 ? a : b; // n≥2 조건이지만 안전하게
    }
}
