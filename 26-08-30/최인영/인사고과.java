import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        Map<Integer, int[]> scoreMap = new HashMap<>();
        Map<Integer, Integer> totalScoreMap = new HashMap<>();

        for(int i = 0; i < scores.length; i++){
            scoreMap.put(i, scores[i]);
        }
        List<Map.Entry<Integer, int[]>> hashValues = new ArrayList<>(scoreMap.entrySet());
        hashValues.sort((o1, o2) -> (o1.getValue()[0] == o2.getValue()[0]) ? (o1.getValue()[1]-o2.getValue()[1]) : (o2.getValue()[0] - o1.getValue()[0]));
        
        int maxPeer = -1;
        for(int i = 0; i < scores.length; i++){
            int[] current = hashValues.get(i).getValue();
            boolean incentive = true;
            // 이전에 지나온 사람들이 근무 태도 점수와 동료 평가 점수 모두 i보다 높은 경우
            // 이미 정렬했으므로 지나온 사람들만 보면 됨
            // 점수  중 하나라도 둘 다 큰 사람 있으면 어차피 인센 못 받으므로 여태 지나온 사람들 중 동료 평가 점수 가장 큰 것 계속 저장하고 비교! (시간 초과 제거 위함)
            if(current[1] < maxPeer){
                incentive = false;
            }

            maxPeer = Math.max(current[1], maxPeer);

            if(!incentive){
                totalScoreMap.put(hashValues.get(i).getKey(), -1);
            }
            else{
                totalScoreMap.put(hashValues.get(i).getKey(), current[0] + current[1]);
            }
        }

        int targetScore = totalScoreMap.get(0);

        if(targetScore == -1){ return -1; }
        return (int)totalScoreMap.values().stream().filter(o -> o > targetScore).count() + 1;
    }
}