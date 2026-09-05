import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { // 0 index, 1 value
            return a[1] - b[1];
        });
        
        if(k >= enemy.length)
        {
            return answer;
        }
        
        int idx = 0;
        do {
            while(pq.size() <= k && idx < enemy.length)
            {
                pq.offer(new int[]{idx, enemy[idx]});
                idx++;
            }
            
            if(pq.isEmpty())
            {
                return answer;
            }
            
            int[] node = pq.poll();
            n -= node[1];
            
            if(n < 0)
            {
                return idx - 1;
            }
            
        } while(idx < enemy.length);
        
        return answer;
    }
}
