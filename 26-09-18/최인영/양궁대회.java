class Solution {

    // 라이언이 10, 9, 8... 0 중 어디에 쐈는지 저
    static int[] lion;
    // dfs 중 찾은 라이언의 최고기록 화살 배치 저장
    static int[] answer;
    // 라이언-어피치의 가장 큰 점수 차이 저장
    static int maxDiff;

    public int[] solution(int n, int[] info) {
        
        lion = new int[11];
        answer = new int[11];
        maxDiff = 0;

        // dfs 탐색 -> lion[0]인 10점 영역부터 n발 가지고 
        dfs(0, n, info);

        // dfs 후 maxDiff가 갱신되지 않고 0 그대로 -> 라이언이 어피치 이긴 경우 x 
        // -> -1 반환
        if(maxDiff == 0) {
            return new int[]{-1};
        }

        // 라이언이 이길 수 있다면 라이언 최고 기록 화살 배치 반환
        return answer;
    }

    // lion[index] 영역에서 탐색, 남은 화살 개수, 어피치 활 쏜 기록으로 탐색 
    static void dfs(int index, int remainArrow, int[] info) {
        // 탐색 완료
        if(index == 11) {

            // 남은 화살은 0점에 몰아줌
            lion[10] += remainArrow;

            // 라이언 점수 - 어피치 점수
            int diff = getScoreDiff(info);

            // 라이언 최고 기록 갱신
            if(diff > maxDiff) {
                maxDiff = diff;
                answer = lion.clone();
            }

            // 라이언이 이겼는데 점수 차이 최고 기록과 같은 경우 -> 더 낮은 점수 많이 맞힌 경우를 선택해야 함
            else if(diff == maxDiff && diff > 0) {
                if(isBetter()) {
                    answer = lion.clone();
                }
            }

            // 이전에 했던 lion[10] += remainArrow 되돌리는 백트레킹
            lion[10] -= remainArrow;

            return;
        }

        // 현재 점수를 먹으려면 몇 발이나 쏴야 하는지 계산하는 과정
        // 라이언이 가져오려면 어피치보다 최소 한 발은 더 쏴야 함
        int need = info[index] + 1;

        // 남은 화살 수가 점수 가져오기 위해 필요한 최소량 만큼 있다면
        if(remainArrow >= need) {

            lion[index] = need;

            dfs(
                index + 1,
                remainArrow - need,
                info
            );
            // 이 점수 먹는 경우 계산 다 했으니까 아닌 경우도 계산하기 위해 백트레킹
            lion[index] = 0;
        }

        // 현재 점수 포기
        dfs(index + 1, remainArrow, info);
    }

    // 둘의 점수차 구함
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

    // 만약 점수차 똑같아서 동점일 때 문제 조건 대로 더 낮은 점수를 많이 맞춘 경우 찾는 것
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
