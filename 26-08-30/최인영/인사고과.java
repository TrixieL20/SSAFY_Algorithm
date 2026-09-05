import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        // 배열 인덱스 + 점수 저장
        Map<Integer, int[]> scoreMap = new HashMap<>();
        // 나중에 등수 계산 위해 배열 인덱스 + 점수 합계 (인센 없는 경우 -1) 저장
        Map<Integer, Integer> totalScoreMap = new HashMap<>();

        for(int i = 0; i < scores.length; i++){
            scoreMap.put(i, scores[i]);
        }
        // 점수 1은 내림차순, 점수 2는 오름차순 정렬!
        List<Map.Entry<Integer, int[]>> hashValues = new ArrayList<>(scoreMap.entrySet());
        hashValues.sort((o1, o2) -> (o1.getValue()[0] == o2.getValue()[0]) ? (o1.getValue()[1]-o2.getValue()[1]) : (o2.getValue()[0] - o1.getValue()[0]));
        
        int maxPeer = -1;
        
        // 근무 태도 점수와 동료 평가 점수 비교 -> 현재 사람보다 둘 다 큰 사람 있는지를 확인하는 것
        // 이미 정렬했으므로 이미 지나온 사람들만 보면 됨 (뒷 사람의 경우 점수 1 이미 작거나 같으므로 x)
        for(int i = 0; i < scores.length; i++){
            int[] current = hashValues.get(i).getValue();
            boolean incentive = true;

            // 지나온 사람 중 하나라도 둘 다 큰 사람 있으면 어차피 인센 못 받으므로 더 볼 필요 x
            // 여태 지나온 사람들 중 점수 2 가장 큰 것 업데이트해 가면서 그것과만 비교! (이중 반복문으로 처리할 경우 O(n^2)로 시간 초과 떴었음)

            // 단, 점수 1끼리 서로 같았던 경우가 문제 됨 ! 이 경우는 인센 제거 되면 안 되는데 max는 점수 2로만 거름 
            // -> 처음 정렬할 때 점수 2는 오름차순 정렬한 걸로 미리 해결 함
            // (ex. (10, 3) (10, 5)로 정렬 & (10, 5)가 현재 사람인 경우: (10, 3)으론 maxPeer > 5 만들 수 없어 인센 제거 불가 !)
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