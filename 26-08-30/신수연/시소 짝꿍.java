import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        // 같은 몸무게가 몇 명 있는지 저장
        Map<Integer, Long> map = new HashMap<>();

        for (int w : weights) {
            map.put(w, map.getOrDefault(w, 0L) + 1);
        }

        for (int w : map.keySet()) {
            long count = map.get(w);

            // 같은 몸무게는 같은 거리에 앉으면 균형을 이룬다.
            // w가 count명 있다면 그중 2명을 고르는 모든 경우의 수를 더한다.
            answer += count * (count - 1) / 2;

            // 시소의 좌석 거리는 2m, 3m, 4m이다.
            // 2m와 4m에 앉는 경우:
            // w × 4 = 다른 몸무게 × 2
            // → 다른 몸무게 = w × 2
            answer += count * map.getOrDefault(w * 2, 0L);

            // 2m와 3m에 앉는 경우:
            // w × 3 = 다른 몸무게 × 2
            // → 다른 몸무게 = w × 3 / 2
            // 몸무게가 정수가 되도록 w가 짝수인 경우만 확인한다.
            if (w % 2 == 0) {
                answer += count * map.getOrDefault(w / 2 * 3, 0L);
            }

            // 3m와 4m에 앉는 경우:
            // w × 4 = 다른 몸무게 × 3
            // → 다른 몸무게 = w × 4 / 3
            // 몸무게가 정수가 되도록 w가 3의 배수인 경우만 확인한다.
            if (w % 3 == 0) {
                answer += count * map.getOrDefault(w / 3 * 4, 0L);
            }
        }

        return answer;
    }
}
