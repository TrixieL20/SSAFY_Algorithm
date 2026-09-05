import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        // (몸무게, 인원수) 저장하는 hashmap 
        Map<Integer, Integer> weightCount = new HashMap<>();
        for(int w : weights){
            // 몸무게 w = weightCounts인 경우 세기 
            answer += weightCount.getOrDefault(w, 0);
            // w = weightCounts * 2인 경우 세기 
            if(w % 2 == 0){
                answer += weightCount.getOrDefault(w / 2, 0);
            }
            // w * 2 = weightCounts * 3인 경우 세기 
            if(2 * w % 3 == 0){
                answer += weightCount.getOrDefault(w * 2 / 3, 0);
            }
            // w * 3 = weightCounts * 4인 경우 세기 
            if(3 * w % 4 == 0){
                answer += weightCount.getOrDefault(w * 3 / 4, 0);
            }
            // 해당 weight도 weightCount에 추가
            weightCount.put(w, weightCount.getOrDefault(w, 0) + 1);
        }
        return answer; 
    }
}