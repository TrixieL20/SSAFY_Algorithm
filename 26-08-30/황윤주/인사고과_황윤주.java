import java.util.*;

class Solution {

    public int solution(int[][] scores) {

        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(b[0], a[0]);
        });

        int max = 0;
        int rank = 1;

        for (int[] score : scores) {

            // 인센티브를 받을 수 없는 직원
            if (score[1] < max) {

                // 완호인지 확인
                if (score[0] == wanhoA && score[1] == wanhoB) {
                    return -1;
                }

                continue;
            }

            // 두 번째 점수의 최대값 갱신
            max = score[1];

            // 완호보다 총점이 높으면 등수 증가
            if (score[0] + score[1] > wanhoSum) {
                rank++;
            }
        }

        return rank;
    }
}
