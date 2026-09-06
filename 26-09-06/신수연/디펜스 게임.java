import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int answer = 0;
        for(int e : enemy) {
            n -= e;
            
            pq.offer(e);
            
            if(n < 0 && k > 0) {
                n += pq.poll();
                k--;
            }
            else if(n < 0 && k == 0) return answer;
            
            answer++;
        }
        return answer;
    }
}
