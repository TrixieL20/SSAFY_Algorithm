import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int w : weights) {

            // 1 : 1
            answer += map.getOrDefault(w, 0);

            // 2 : 3
            if (w * 2 % 3 == 0) {
                answer += map.getOrDefault(w * 2 / 3, 0);
            }

            if (w * 3 % 2 == 0) {
                answer += map.getOrDefault(w * 3 / 2, 0);
            }

            // 1 : 2
            answer += map.getOrDefault(w * 2, 0);

            if (w % 2 == 0) {
                answer += map.getOrDefault(w / 2, 0);
            }

            // 3 : 4
            if (w * 3 % 4 == 0) {
                answer += map.getOrDefault(w * 3 / 4, 0);
            }

            if (w * 4 % 3 == 0) {
                answer += map.getOrDefault(w * 4 / 3, 0);
            }

            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        return answer;
    }
}
