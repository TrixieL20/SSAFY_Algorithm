import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights); // 오름차순 정렬

        Map<Double, Integer> map = new HashMap<>(); // 무게 weight, 무게 갯수

        for (int w : weights) {
            double a = w * 1.0; // 무게가 같은 경우
            double b = (w * 2.0) / 3.0; // 2/3인 경우
            double c = (w * 1.0) / 2.0; // 2/4인 경우
            double d = (w * 3.0) / 4.0; // 3/4인 경우

            // 기존 key값과 같으면 체크해 정답 + 1
            if (map.containsKey(a)) answer += map.get(a);
            if (map.containsKey(b)) answer += map.get(b);
            if (map.containsKey(c)) answer += map.get(c);
            if (map.containsKey(d)) answer += map.get(d);

            map.put((w * 1.0), map.getOrDefault((w * 1.0), 0) + 1);
        }

        return answer;
    }
}