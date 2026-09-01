import java.util.Arrays;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        // 광물 5개씩 캐므로 5개씩 나누기
        // 만약 광물 개수가 곡괭이가 캘 수 있는 양보다 많다면곡괭이 개수 * 5만큼만 확인하면 됨
        // 곡괭이 개수
        int picks_count = picks[0] + picks[1] + picks[2];
        int digging_count = Math.min(picks_count * 5, minerals.length);
        int digging_groups = (int)Math.ceil((double)digging_count / 5);
        
        // 광물 5개씩 묶어 다이아/ 철/돌 개수 세어 digged_mins에 저장
        int[][] digged_mins = new int[digging_groups][3];
        for(int i = 0; i < digging_count; i++){
            switch (minerals[i]) {
                case "diamond":
                    digged_mins[i / 5][0]++;
                    break;
                case "iron":
                    digged_mins[i / 5][1]++;
                    break;
                default:
                    digged_mins[i / 5][2]++;
                    break;
            }
        }
        
        // 5개씩 묶어 나눈 광물 중 다이아가 가장 많은 것부터 
        // -> 다이아 개수 같다면 철 개수 가장 많은 것 순으로 좋은 곡갱이 사용하기 위해 정렬
        Arrays.sort(digged_mins, (a,b) -> (a[0] == b[0]) ? b[1] - a[1] : b[0] - a[0]);

        // 곡괭이에 따른 피로도 계산
        int[][] fatigue = {
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
        };

        int picks_index = 0 ;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <picks[i]; j++){
                if(picks_index == digging_groups){ return answer; }
                for(int y = 0; y < 3; y++){
                    answer += fatigue[i][y] * digged_mins[picks_index][y];
                }
                picks_index++;
            }
        }
        return answer;
    }
}