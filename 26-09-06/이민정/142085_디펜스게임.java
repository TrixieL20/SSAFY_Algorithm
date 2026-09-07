import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < Math.min(enemy.length, k); i++) {
            pq.add(enemy[i]);
        }

        int idx = k, answer = 0;

        // k 라운드는 보장되므로 그 이후의 적 중 작은 값들만 골라서 처치하면서 라운드 진행 여부 판단
        // n을 써서 진행하는 라운드의 수만 계산
        while (true) {
            if (pq.isEmpty()) break;

            if (idx < enemy.length) {
                pq.add(enemy[idx++]);
            }

            int enemyCnt = pq.poll();

            // 무적권을 쓰지 않고 적을 처치할 수 없는 경우
            if (n - enemyCnt < 0) {
                pq.add(enemyCnt); // answer에 무적권 수와 남은 적의 수 중 최솟값을 더할 거라서 복원함
                break;
            }

            n -= enemyCnt;
            answer++;
        }

        // 무적권 써서 이기는 라운드
        if (!pq.isEmpty()) {
            answer += Math.min(pq.size(), k);
        }

        return answer;
    }
}