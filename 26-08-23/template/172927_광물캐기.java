import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution.solution(picks, minerals));;
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {

        int pickCnt = 0;

        for (int pick: picks) {
            pickCnt += pick;
        }

        int digCnt = Math.min(pickCnt * 5, minerals.length);
        int bundleCnt = digCnt % 5 == 0 ? digCnt / 5 : digCnt / 5 + 1;

        int[][] bundles = new int[bundleCnt][picks.length];

        for (int i = 0; i < digCnt; i++) {
            int bundleSeq = i / 5;

            if (minerals[i].equals("diamond")) {
                bundles[bundleSeq][0]++;
            }

            else if (minerals[i].equals("iron")) {
                bundles[bundleSeq][1]++;
            }

            else if (minerals[i].equals("stone")) {
                bundles[bundleSeq][2]++;
            }
        }

        Arrays.sort(bundles, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]);
            }

            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }

            return  Integer.compare(b[2], a[2]);
        });

        int answer = 0;

        for (int i = 0; i < bundleCnt; i++) {
            if (picks[0] > 0) {
                answer += digMinerals(0, i, bundles);
                picks[0]--;
            }

            else if (picks[1] > 0) {
                answer += digMinerals(1, i, bundles);
                picks[1]--;
            }

            else if (picks[2] > 0) {
                answer += digMinerals(2, i, bundles);
                picks[2]--;
            }
        }

        return answer;
    }

    private int digMinerals(int pick, int bundleSeq, int[][] bundles) {
        int fatigue = 0;

        // 다이아
        if (pick == 0) {
            for (int j = 0; j < bundles[0].length; j++) {
                fatigue += bundles[bundleSeq][j];
            }
        }

        // 철
        else if (pick == 1) {
            for (int j = 0; j < bundles[0].length; j++) {
                if (j == 0) {
                    fatigue += bundles[bundleSeq][j] * 5;
                    continue;
                }
                fatigue += bundles[bundleSeq][j];
            }
        }

        // 돌
        else if (pick == 2) {
            for (int j = 0; j < bundles[0].length; j++) {
                switch (j) {
                    case 0:
                        fatigue += bundles[bundleSeq][j] * 25;
                        break;
                    case 1:
                        fatigue += bundles[bundleSeq][j] * 5;
                        break;
                    default:
                        fatigue += bundles[bundleSeq][j];
                }
            }
        }

        return fatigue;
    }
}