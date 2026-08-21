class Solution {
    static final int MODULAR = 10007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        int a, b, na, nb;
        a = tops[0] == 1 ? 3 : 2;
        b = 1;
        for(int i = 1; i < n; i++)
        {  
            na = (a * (tops[i] == 1 ? 3 : 2)) % MODULAR + (b * (tops[i] == 1 ? 2 : 1)) % MODULAR;
            nb = (a + b) % MODULAR;
            a = na;
            b = nb;
        }
        answer = (a + b) % MODULAR;
        
        return answer;
    }
}
