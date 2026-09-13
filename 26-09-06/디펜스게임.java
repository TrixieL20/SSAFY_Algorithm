import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 최소 힙: 무적권 후보(적 수 상위 k개를 유지하기 위한 그릇)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long used = 0; // 힙에서 밀려나 "병사로 막아야 확정된" 적 수의 누적 합

        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]); // 이번 라운드도 일단 무적권 후보로 넣는다

            if (pq.size() > k) {
                // 후보가 k개를 초과 -> 가장 작은 값은 무적권 자격을 잃고
                // "병사로 막는 라운드"로 확정 -> 이때 비로소 used에 더한다
                int minInHeap = pq.poll();
                used += minInHeap;
            }

            if (used > n) {
                // 확정된(병사로 막아야 하는) 적 수 합이 병사 수를 초과 -> i라운드에서 실패
                return i;
            }
        }

        return enemy.length;
    }
}