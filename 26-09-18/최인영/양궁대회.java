class Solution {

    static int[] lion;
    static int[] answer;
    static int maxDiff;

    public int[] solution(int n, int[] info) {

        lion = new int[11];
        answer = new int[11];
        maxDiff = 0;

        dfs(0, n, info);

        if(maxDiff == 0) {
            return new int[]{-1};
        }

        return answer;
    }

    static void dfs(int index, int remainArrow, int[] info) {

        if(index == 11) {

            // 남은 화살은 0점에 몰아줌
            lion[10] += remainArrow;

            int diff = getScoreDiff(info);

            if(diff > maxDiff) {
                maxDiff = diff;
                answer = lion.clone();
            }

            else if(diff == maxDiff && diff > 0) {

                if(isBetter()) {
                    answer = lion.clone();
                }
            }

            lion[10] -= remainArrow;

            return;
        }

        // 현재 점수 가져오기
        int need = info[index] + 1;

        if(remainArrow >= need) {

            lion[index] = need;

            dfs(
                index + 1,
                remainArrow - need,
                info
            );

            lion[index] = 0;
        }

        // 현재 점수 포기
        dfs(index + 1, remainArrow, info);
    }

    static int getScoreDiff(int[] info) {

        int lionScore = 0;
        int apeachScore = 0;

        for(int i = 0; i < 11; i++) {

            if(lion[i] == 0 && info[i] == 0) {
                continue;
            }

            int score = 10 - i;

            if(lion[i] > info[i]) {
                lionScore += score;
            }
            else {
                apeachScore += score;
            }
        }

        return lionScore - apeachScore;
    }

    static boolean isBetter() {

        for(int i = 10; i >= 0; i--) {

            if(lion[i] > answer[i]) {
                return true;
            }

            if(lion[i] < answer[i]) {
                return false;
            }
        }

        return false;
    }
}