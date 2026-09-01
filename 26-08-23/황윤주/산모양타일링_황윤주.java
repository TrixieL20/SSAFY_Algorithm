class Solution {

    static final int MOD = 10007;

    public int solution(int n, int[] tops) {

        long[][] dp = new long[n][2];

        // 첫 번째 열
        if (tops[0] == 1) {
            dp[0][0] = 3;
            dp[0][1] = 1;
        } else {
            dp[0][0] = 2;
            dp[0][1] = 1;
        }

        for (int i = 1; i < n; i++) {

            if (tops[i] == 1) {

                dp[i][0] =
                        (dp[i - 1][0] * 3
                                + dp[i - 1][1] * 2)
                                % MOD;

            } else {

                dp[i][0] =
                        (dp[i - 1][0] * 2
                                + dp[i - 1][1])
                                % MOD;
            }

            dp[i][1] =
                    (dp[i - 1][0]
                            + dp[i - 1][1])
                            % MOD;
        }

        return (int)
                ((dp[n - 1][0]
                        + dp[n - 1][1])
                        % MOD);
    }
}