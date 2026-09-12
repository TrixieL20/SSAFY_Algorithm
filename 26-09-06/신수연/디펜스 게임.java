import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int answer = 0;
        for(int e : enemy) {
            n -= e;
            
            pq.offer(e);

            // n이 0보다 작고 무적권 k가 0보다 클 때
            // 무적권 사용
            if(n < 0 && k > 0) {
                n += pq.poll();
                k--;
            }
            // n이 0보다 작고 무적권도 모두 사용했을 때 라운드를 카운트한 answer 리턴
            else if(n < 0 && k == 0) return answer;
            
            answer++;
        }
        return answer;
    }
}
