class Solution {
    private int maxDiff = 0;
    private int[] answer = {-1};
    
     private void dfs(int score, int arrows, int apeachScore, int ryanScore,
                     int[] ryan, int[] info) {

        // 10점 ~ 0점까지 모두 결정
        if (score == 11) {

            // 남은 화살은 0점에 몰아준다.
            ryan[10] += arrows;

            int diff = ryanScore - apeachScore;

            if (diff > 0) {
                if (diff > maxDiff || 
                    (diff == maxDiff && isBetter(ryan, answer))) {

                    maxDiff = diff;
                    answer = ryan.clone();
                }
            }

            ryan[10] -= arrows;
            return;
        }

        // 1. 라이언이 현재 점수를 가져가는 경우
        // info[score] + 1개의 화살 필요
        int required = info[score] + 1;

        if (arrows >= required) {
            ryan[score] = required;

            dfs(
                score + 1,
                arrows - required,
                apeachScore,
                ryanScore + (10 - score),
                ryan,
                info
            );

            ryan[score] = 0;
        }

        // 2. 라이언이 현재 점수를 포기하는 경우
        if (info[score] > 0) {
            dfs(
                score + 1,
                arrows,
                apeachScore + (10 - score),
                ryanScore,
                ryan,
                info
            );
        } else {
            // 어피치가 0발 쏜 점수라면
            // 아무도 점수를 얻지 못한다.
            dfs(
                score + 1,
                arrows,
                apeachScore,
                ryanScore,
                ryan,
                info
            );
        }
    }

    // 낮은 점수를 더 많이 맞힌 배열이 우선
    private boolean isBetter(int[] ryan, int[] answer) {
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > answer[i]) {
                return true;
            }

            if (ryan[i] < answer[i]) {
                return false;
            }
        }

        return false;
    }

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];

        dfs(0, n, 0, 0, ryan, info);

        return answer;
    }
}
