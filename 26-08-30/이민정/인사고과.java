import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int size = scores.length;
        int n = scores[0][0];
        int m = scores[0][1];

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        // 어차피 근무 태도는 내림차순으로 정렬되어 있으므로 동료 점수만 비교
        int maxScore = scores[0][1];

        for(int i = 1; i<size; i++) {
            if (scores[i][1] < maxScore) { // 인센티브를 받지 못하는 경우
                if (scores[i][0] == n && scores[i][1] == m) // 완호 점수인 경우
                    return -1;

                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                maxScore = scores[i][1];
            }
        }

        // 합을 기준으로 정렬
        Arrays.sort(scores, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));

        answer = 1;

        // 완호보다 높은지만 체크하면 등수가 나온다
        for(int i = 0; i<size; i++) {
            if (scores[i][0] + scores[i][1] > n + m) {
                answer ++;
            } else {
                break;
            }
        }

        return answer;
    }
}