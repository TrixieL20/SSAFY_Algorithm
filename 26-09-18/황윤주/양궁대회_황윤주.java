import java.util.*;

class Solution {

    static int[] answer = {-1};
    static int[] ryan = new int[11];

    static int maxDiff = 0;
    static int N;
    static int[] apeach;

    public int[] solution(int n, int[] info) {
        N = n;
        apeach = info;

        dfs(0, n);

        return answer;
    }

    // idx : 현재 확인할 점수 인덱스
    // remain : 남은 화살 개수
    static void dfs(int idx, int remain) {

        // 0점까지 확인했거나 화살을 다 쓴 경우
        if (idx == 11 || remain == 0) {

            // 남은 화살이 있다면 0점에 몰아주기
            if (remain > 0) {
                ryan[10] += remain;
            }

            checkScore();

            // 원상복구
            if (remain > 0) {
                ryan[10] -= remain;
            }

            return;
        }


        // 1. 현재 점수를 라이언이 가져가는 경우
        // 어피치보다 딱 1발 많이 쏘면 됨

        int need = apeach[idx] + 1;

        if (remain >= need) {

            ryan[idx] = need;

            dfs(idx + 1, remain - need);

            // 백트래킹
            ryan[idx] = 0;
        }


        // 2. 현재 점수를 포기하는 경우

        dfs(idx + 1, remain);
    }

    static void checkScore() {

        int ryanScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < 11; i++) {

            // 둘 다 0발이면 아무도 점수 못 얻음
            if (ryan[i] == 0 && apeach[i] == 0) {
                continue;
            }

            int score = 10 - i;

            if (ryan[i] > apeach[i]) {
                ryanScore += score;
            } else {
                apeachScore += score;
            }
        }

        int diff = ryanScore - apeachScore;

        // 라이언이 이긴 경우만 확인
        if (diff <= 0) {
            return;
        }

        // 더 큰 점수차 발견
        if (diff > maxDiff) {
            maxDiff = diff;
            answer = ryan.clone();
        }

        // 점수차가 같은 경우
        else if (diff == maxDiff) {

            if (isBetter(ryan, answer)) {
                answer = ryan.clone();
            }
        }
    }

    // 더 낮은 점수를 많이 맞힌 경우인지 확인
    static boolean isBetter(int[] current, int[] answer) {

        // 0점부터 역순으로 확인
        for (int i = 10; i >= 0; i--) {

            if (current[i] > answer[i]) {
                return true;
            }

            if (current[i] < answer[i]) {
                return false;
            }
        }

        return false;
    }
}