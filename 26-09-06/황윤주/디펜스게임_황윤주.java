import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {

            // 일단 병사로 막았다고 가정
            n -= enemy[i];

            // 지금까지 나온 적 수 저장
            pq.offer(enemy[i]);

            // 병사가 부족해졌다면
            if (n < 0) {

                // 무적권이 없으면 현재 라운드에서 종료
                if (k == 0) {
                    return i;
                }

                // 지금까지 가장 적이 많았던 라운드에
                // 무적권을 사용했다고 변경
                n += pq.poll();
                k--;
            }
        }

        return enemy.length;
    }
}