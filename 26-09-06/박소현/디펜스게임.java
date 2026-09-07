import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        
        if(k >= enemy.length)
        {
            return answer;
        }
        
        int idx = 0;
        do {
            while(pq.size() <= k && idx < enemy.length)
            {
                pq.offer(enemy[idx]);
                idx++;
            }
            
            if(pq.isEmpty())
            {
                return answer;
            }
            
            int node = pq.poll();
            n -= node;
            
            if(n < 0)
            {
                return idx - 1;
            }
            
        } while(idx < enemy.length);
        
        return answer;
    }
}
