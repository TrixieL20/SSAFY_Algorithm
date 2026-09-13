import java.util.Arrays;

class Solution {
    static int maxDiff = 0;
    static int[] lionScore = new int[11];
    static int[] apeachScore = null;
    static int[] result = null;

    public int[] solution(int n, int[] info) {
        apeachScore = info;

        dfs(n, 10);

        if (maxDiff == 0) result = new int[] {-1};
        return result;
    }

    private void dfs (int remainRound, int idx) {
        if (remainRound == 0 || idx == 0) {
            lionScore[idx] = remainRound;

            int diff = calculateDiff();

            if (diff > maxDiff)  {
                maxDiff = diff;
                result = Arrays.copyOf(lionScore, lionScore.length);
            }

            else if (maxDiff != 0 && diff == maxDiff) {
                for (int i = lionScore.length - 1; i >= 0; i--) {
                    if (lionScore[i] > result[i]) {
                        result = Arrays.copyOf(lionScore, lionScore.length);
                        break;
                    }

                    if (lionScore[i] < result[i]) {
                        break;
                    }
                }
            }

            return;
        }

        for (int i = 0; i <= remainRound; i++) {
            lionScore[idx] = i;
            dfs(remainRound - i, idx - 1);
        }
    }

    private int calculateDiff() {
        int apeach = 0, lion = 0;

        for (int i = 0; i < lionScore.length; i++) {
            if (apeachScore[i] != 0 && apeachScore[i] >= lionScore[i]) apeach += (10 - i);
            else if (lionScore[i] != 0) lion += (10 - i);
        }

        return lion - apeach;
    }
}