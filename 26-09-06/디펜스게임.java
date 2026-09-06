import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 최소 힙: 현재까지 "무적권 후보"로 간주되는 라운드들의 적 수 저장
        // (힙 안에 남아있는 값들 = 무적권으로 막았다고 가정하는 적 수)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long used = 0; // 무적권으로 커버되지 않아 병사로 막아야 하는 적 수의 누적 합

        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);   // 이번 라운드도 일단 무적권 후보로 넣는다
            used += enemy[i];     // 누적합에도 임시로 더한다 (아직 확정 아님)

            if (pq.size() > k) {
                // 무적권은 최대 k번만 사용 가능 → 힙 크기가 k를 초과하면
                // 후보 중 "가장 작은 적 수"를 병사 소모 쪽으로 확정 이동
                // (큰 값일수록 무적권을 쓰는 게 항상 유리하므로,
                //  상위 k개만 힙에 남기고 나머지는 병사로 처리)
                int minInHeap = pq.poll();
                used -= minInHeap;
            }

            if (used > n) {
                // 병사로 막아야 할 누적 합이 보유 병사 수를 초과
                // → i번째 라운드(0-indexed)에서 게임 종료
                // 여기까지 무사히 막은 라운드 수 = i (0~i-1 라운드 성공)
                return i;
            }
        }

        // 마지막 라운드까지 전부 막아낸 경우
        return enemy.length;
    }
}