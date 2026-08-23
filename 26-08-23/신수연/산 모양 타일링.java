class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n + 1][2];

        dp[0][0] = 0;
        dp[0][1] = 1;

        for (int i = 1; i <= n; i++) {

            // 오른쪽 아래 삼각형이 마름모에 포함되는 경우
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;

            // 현재 위쪽 삼각형이 있는지에 따라 경우의 수가 달라짐
            if (tops[i - 1] == 1) {
                dp[i][1] =
                    (2 * dp[i - 1][0] + 3 * dp[i - 1][1]) % 10007;
            } else {
                dp[i][1] =
                    (dp[i - 1][0] + 2 * dp[i - 1][1]) % 10007;
            }
        }

        return (dp[n][0] + dp[n][1]) % 10007;
    }
}
