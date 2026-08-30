import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        // (몸무게, 인원수) 저장하는 hashmap 
        Map<Integer, Integer> weightCount = new HashMap<>();
        for(int w : weights){
            answer += weightCount.getOrDefault(w, 0) + 1;
            weightCount.put(w, weightCount.getOrDefault(w,0) + 1);
        }
        return answer; 
    }

    // 매개변수로 들어온 weight에 대해 2, 3, 4를 곱해 시소에 걸리는 토크의 크기 만듦
    // -> sisoCount에 중복 key값(이전에 동일 토크 가해진 적 있는지 체크)
    // -> 없었다면 새로 추가, 있었다면 해당 토크를 key로 가지는 value를 +1
    Boolean checkWeight(List<Integer> torques, int weight){
        int[] dist = {2, 3, 4};
        for(int d: dist){
            if(torques.contains(d * weight)){ return true; }
            else{
                torques.add(d * weight);
            }
        }
        return false;
    }
}