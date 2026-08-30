import java.util.*;

class Solution {
    public int solution(int[][] scores) {

        int attitude = scores[0][0];
        int peer = scores[0][1];
        int total = attitude + peer;

        // 근무 태도 점수 내림차순
        // 근무 태도 점수가 같으면 동료 평가 점수 오름차순
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        int maxPeer = 0;
        int rank = 1;

        for (int[] score : scores) {

            // 현재 직원의 동료 평가 점수가
            // 앞에서 확인한 직원들의 최댓값보다 작으면 인센티브 대상에서 제외
            if (score[1] < maxPeer) {

                // 완호가 인센티브 대상에서 제외된 경우
                if (score[0] == attitude && score[1] == peer) {
                    return -1;
                }

                continue;
            }

            // 현재까지의 동료 평가 점수 최댓값 갱신
            maxPeer = Math.max(maxPeer, score[1]);

            // 완호보다 총점이 높은 직원이면 순위 증가
            if (score[0] + score[1] > total) {
                rank++;
            }
        }

        return rank;
    }
}
